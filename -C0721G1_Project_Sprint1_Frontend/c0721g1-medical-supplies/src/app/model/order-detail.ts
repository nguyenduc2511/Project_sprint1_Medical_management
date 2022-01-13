import {CustomerTransfer} from './customerTransfer';
import {Supplies} from './supplies';

export class OrderDetail {
  id: number;
  // tslint:disable-next-line:variable-name
  order_date: string;
  // tslint:disable-next-line:variable-name
  total_money: number;
  quantity: number;
  customer: CustomerTransfer;
  supplies: Supplies;
}
