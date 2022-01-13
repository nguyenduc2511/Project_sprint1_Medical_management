import {Cart} from './cart';
import {CustomerTransfer} from './customerTransfer';

export class Payment {
  cartList: Cart[];
  customerTransfer: CustomerTransfer;

  constructor(cartList: Cart[], customerTransfer: CustomerTransfer) {
    this.cartList = cartList;
    this.customerTransfer = customerTransfer;
  }
}
