// @ts-ignore
import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {AddressService} from '../service/address.service';
import {Address} from '../model/address';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {CustomerTransfer} from '../model/customerTransfer';
import {SuppliesService} from '../service/supplies.service';
import {Cart} from '../model/cart';
import {Payment} from '../model/payment';
import {FinancialService} from '../service/financial.service';

declare var paypal;

// @ts-ignore
@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  constructor(
    private router: Router,
    private financialService: FinancialService,
    private suppliesService: SuppliesService,
    private activatedRoute: ActivatedRoute,
    private toastrService: ToastrService,
    private addressService: AddressService) {
    this.cartList = this.suppliesService.getCartList();
    this.getTotalMoney();
    this.addressService.getList().subscribe(
      value => {
        this.addressList = value;
      },
      error => {
        console.log(error);
      }
    );
  }

  get name() {
    return this.customerForm.get('name');
  }

  get email() {
    return this.customerForm.get('email');
  }

  get address() {
    return this.customerForm.get('address');
  }

  get phone() {
    return this.customerForm.get('phone');
  }

  @ViewChild('paypal', {static: true}) paypalElement: ElementRef;

  cartList: Cart[] = [];
  customerTransfer: CustomerTransfer;
  paidFor = false;
  total: number;
  flag: boolean;
  addressList: Address[] = [];
  customerForm: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required, this.validateName, Validators.maxLength(50)]),
    phone: new FormControl('', [Validators.required, Validators.pattern(/^0(\d){9}$/)]),
    email: new FormControl('', [Validators.required, Validators.maxLength(50),
      Validators.pattern(/^[a-z]+[a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+\\.*[a-zA-Z0-9])*/)]),
    address: new FormControl('', [Validators.required, this.validateName]),
  });
  ngOnInit(): void {
    paypal.Buttons({
      createOrder: (data, actions) => {
        return actions.order.create({
          purchase_units: [
            {
              description: 'hế lô a e',
              amount: {
                currency_code: 'USD',
                value: this.total
              }
            }
          ]
        });
      },
      onApprove: async (data, actions) => {
        const order = await actions.order.capture();
        this.paidFor = true;
        // @ts-ignore
        this.payment();
        this.router.navigateByUrl('/home/list');
        this.toastrService.success('Bạn đã thanh toán thành công, vui lòng kiểm tra email');
      },
      onError: err => {
      }
    })
      .render(this.paypalElement.nativeElement);
  }

  checkPaypal() {
    this.flag = !this.flag;
  }
  payment() {
    this.customerTransfer = this.customerForm.value;
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < this.addressList.length ; i++) {
      // @ts-ignore
      if ( this.addressList[i].name === this.customerTransfer.address ) {
        this.customerTransfer.address = this.addressList[i];
        console.log(this.customerTransfer);
        break;
      }
    }
    // @ts-ignore
    const pay = new Payment( this.cartList, this.customerTransfer );
    this.suppliesService.payment(pay).subscribe(
      value => {
        this.moveHomePage();
        localStorage.clear();
        // tslint:disable-next-line:no-unused-expression
        this.toastrService.success('Bạn đã đặt hàng thành công, vui lòng kiểm tra email');
        this.financialService.saveNewOrder(this.total);
      },
      error => {
      }
    );
  }

  clickTC() {
    this.toastrService.success('Bạn đã thanh toán thành công, vui lòng kiểm tra email');
  }

  moveHomePage() {
    this.router.navigateByUrl('/home/list');
  }

  moveToCartPage() {
    this.router.navigateByUrl('/cart');
  }

  validateName(name: AbstractControl) {
    return isValidUnicodeFullName(name.value) ? null : {errorName: true};
  }

  getTotalMoney() {
    this.total = 0;
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < this.cartList.length; i++) {
      this.total += this.cartList[i].quantity * this.cartList[i].price;
    }
  }

}



function removeAscent(str) {
  if (str === null || str === undefined) {
    return str;
  }
  str = str.toLowerCase();
  str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, 'a');
  str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, 'e');
  str = str.replace(/ì|í|ị|ỉ|ĩ/g, 'i');
  str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, 'o');
  str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, 'u');
  str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, 'y');
  str = str.replace(/đ/g, 'd');
  return str;
}

function isValidWord(word) {
  // const re = /[_\W]0-9/;
  return /^[a-zA-Z]+$/.test(removeAscent(word));
}

function isValidUnicodeFullName(fullName) {
  const arrName = fullName.split(' ');
  // tslint:disable-next-line:prefer-for-of
  for (let i = 0; i < arrName.length; i++) {
    if (!isValidWord(arrName[i])) {
      return false;
    }
  }
  return true;
}
