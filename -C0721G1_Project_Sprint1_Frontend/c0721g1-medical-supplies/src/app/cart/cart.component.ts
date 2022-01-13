// @ts-ignore
import {Component, DoCheck, OnInit} from '@angular/core';
import {Supplies} from '../model/supplies';
import {SuppliesService} from '../service/supplies.service';
import {Cart} from '../model/cart';
import {Router} from '@angular/router';

import {ToastrService} from 'ngx-toastr';


// @ts-ignore
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor(
    private suppliesService: SuppliesService,
    private router: Router,
    private toastrService: ToastrService
  ) {
    this.getCartList();
  }

  suppliesIdList: string[] = [];
  quantity: number [] = [];
  cartList: Cart[] = [];
  totalMoney = 0;
  idDeleteCart = 0;
  nameDeleteCart = '';
  p = 1;
  getCartList() {
    this.suppliesIdList = Object.keys(localStorage);
    this.getQuantity();
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < this.suppliesIdList.length; i++) {
      this.suppliesService.findById(Number(this.suppliesIdList[i])).subscribe(
        value => {
          const cart = new Cart();
          cart.id = value.id;
          cart.name = value.name;
          cart.price = value.price;
          cart.image = value.image;
          console.log(localStorage.getItem(String(cart.id)));
          cart.quantity = Number(localStorage.getItem(String(cart.id)));
          this.cartList.push(cart);
          this.getTotalMoney(cart.id, cart.quantity);
        });
    }
  }


  getQuantity() {
    for (let i = 0; i < 15; i++) {
      this.quantity.push(i);
    }
  }

  ngOnInit(): void {
  }



  getTotalMoney(id: number, quantity: number) {
    // @ts-ignore
    localStorage.setItem(String(id), String(quantity));
    this.totalMoney = 0;
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < this.cartList.length; i++) {
      this.totalMoney += this.cartList[i].quantity * this.cartList[i].price;
    }
  }

  moveToPaymentPage() {
    this.suppliesService.saveCartListTemp(this.cartList);
  }

  deleteCart(id: number) {
    localStorage.removeItem(String(id));
    this.router.navigateByUrl('/home/list').then(e => {
      if (e) {
        this.router.navigateByUrl('/cart');
      }
    });
  }

  addIdToDelete(id: number, name: string) {
    this.idDeleteCart = id;
    this.nameDeleteCart = name;
  }
}

// function allStorage() {
//
//   // tslint:disable-next-line:prefer-const one-variable-per-declaration
//   let values = [],
//     // tslint:disable-next-line:prefer-const
//     keys = Object.keys(localStorage),
//     i = keys.length;
//
//   while (i--) {
//     values.push(localStorage.getItem(keys[i]));
//   }
//
//   return values;
// }
