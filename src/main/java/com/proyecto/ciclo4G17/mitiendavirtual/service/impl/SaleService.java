package com.proyecto.ciclo4G17.mitiendavirtual.service.impl;

import com.proyecto.ciclo4G17.mitiendavirtual.model.Detail;
import com.proyecto.ciclo4G17.mitiendavirtual.model.Sale;
import com.proyecto.ciclo4G17.mitiendavirtual.model.ShoppingCart;
import com.proyecto.ciclo4G17.mitiendavirtual.repository.SaleRepository;
import com.proyecto.ciclo4G17.mitiendavirtual.security.models.User;
import com.proyecto.ciclo4G17.mitiendavirtual.security.services.UserService;
import com.proyecto.ciclo4G17.mitiendavirtual.service.ISaleService;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class SaleService implements  ISaleService{
    private final SaleRepository saleRepository;
    private final UserService userService;
    private final ShoppingCartService shoppingListService;
    private final DetailService detailService;

    public SaleService(SaleRepository saleRepository, UserService userService,
            ShoppingCartService shoppingListService, DetailService detailService) {
        this.saleRepository = saleRepository;
        this.userService = userService;
        this.shoppingListService = shoppingListService;
        this.detailService = detailService;
    }

    @Override
    public List<Sale> getSaleByClient(String userName) {
        return this.saleRepository.findByClient_UserName(userName);
    }

    @Override
    public void createSale(String userName) {
        User client = this.userService.getByUserName(userName).get();
        List<ShoppingCart> shoppingCartList = this.shoppingListService.getListByClient(client.getUserName());
        //Establecer formato para un decimal
        DecimalFormat decimalFormat = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
        //establecer formato de estados unidos para que no haya problema con el ounto
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        //CALCULAR TOTAL
        double total = shoppingCartList.stream().mapToDouble(
           shoppingCartItem -> shoppingCartItem.getProduct().getPrice() * shoppingCartItem.getAmount()).sum();
        Sale sale = new Sale(Double.parseDouble(decimalFormat.format(total)), new Date(), client );
        Sale saveSale = this.saleRepository.save(sale);
        for (int i = 0; i < shoppingCartList.size(); i++) {
            Detail detail = new Detail();
            detail.setProduct(shoppingCartList.get(i).getProduct());
            detail.setAmount(shoppingCartList.get(i).getAmount());
            detail.setSale(saveSale);
            this.detailService.createDetail(detail);
        }
        this.shoppingListService.cleanShoppingCart(client.getId());
    }
}
