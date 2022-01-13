// @ts-ignore
import { Injectable } from '@angular/core';
// @ts-ignore
import {HttpClient, HttpClientModule, HttpHeaders} from '@angular/common/http';
// @ts-ignore
import {Observable} from 'rxjs';
import {Supplies} from '../model/supplies';
import {CustomerTransfer} from '../model/customerTransfer';
import {Cart} from '../model/cart';
import {Payment} from '../model/payment';
// @ts-ignore
import {PageSuppliesDTO} from '../dto/PageSuppliesDTO';



// @ts-ignore
@Injectable({
  providedIn: 'root'
})
export class SuppliesService {
  httpOptions: any;
  private API_URL = 'http://localhost:8080/api/';
  constructor(private httpClient: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        charset: 'utf-8'
      }),
      'Access-Control-Allow-Origin': 'http://localhost:4200',
      'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
    };
  }
  cartList: Cart[] = [];
  getSuppliesList(page: number): Observable<any> {
    return this.httpClient.get<any>(this.API_URL + 'public/home/list/' + page, this.httpOptions);
  }
  findById(id: number ): Observable<any> {
    return this.httpClient.get<Supplies>(this.API_URL + 'public/home/detail/' + id, this.httpOptions);
  }
  payment(payment: Payment): Observable<any> {
    console.log(payment);
    return this.httpClient.post<any>(this.API_URL + 'public/home/payment', payment, this.httpOptions);
  }
  saveCartListTemp(cartList: Cart[]) {
    this.cartList = cartList;
  }
  getCartList( ) {
    return this.cartList;
  }

  getListSupplies(pageSuppliesDTO: PageSuppliesDTO): Observable<any> {
    const code = pageSuppliesDTO.code;
    const name = pageSuppliesDTO.name;
    const suppliesTypeId = pageSuppliesDTO.suppliesType;
    const page = pageSuppliesDTO.page;
    const size = pageSuppliesDTO.size;
    return this.httpClient.get<any>(this.API_URL + 'admin/supplies/?code=' + code + '&name=' + name + '&suppliesType='
      + suppliesTypeId + '&page=' + page + '&size=' + size, this.httpOptions);
  }
  deleteSupplies(id: number): Observable<any> {
    return this.httpClient.delete<void>(this.API_URL + 'admin/supplies/' + id, this.httpOptions);
  }

  save(supplies: Supplies): Observable<any> {
    return this.httpClient.post<void>(this.API_URL + 'admin/supplies/create', supplies, this.httpOptions);
  }

  update(supplies: Supplies): Observable<any> {
    return this.httpClient.patch<any>(this.API_URL + 'admin/supplies/edit', supplies, this.httpOptions);
  }

  findByIdSupplies(id: number): Observable<any> {
    return this.httpClient.get<Supplies>(this.API_URL + 'admin/supplies/findById/' + id, this.httpOptions);
  }

  getCode(): (any) {
    return this.httpClient.get<any>(this.API_URL + 'admin/supplies/code', this.httpOptions);
  }
  findAll(): Observable<any> {
    return this.httpClient.get<any>(this.API_URL + 'public/home/list');
  }
  searchSupplies(page: number, id: number): Observable<any> {
    return this.httpClient.get<any>(this.API_URL + 'public/list/' + id + '/' + page);
  }
  findAllSupplies(): Observable<any> {
    return this.httpClient.get<any>(this.API_URL + 'public/list');
  }
}
