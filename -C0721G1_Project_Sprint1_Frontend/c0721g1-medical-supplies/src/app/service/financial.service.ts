import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {FinancialStats} from '../model/FinancialStats';

@Injectable({
  providedIn: 'root'
})
export class FinancialService {

  private API = 'http://localhost:8080/api';


  constructor(private http: HttpClient) {

  }

  totalMoney: number;

  saveNewOrder(total: any): void {
    this.totalMoney = total;
  }

  getTotal(): any {
    console.log(this.totalMoney);
    return this.totalMoney;
  }

  getAll(): Observable<FinancialStats | any> {
    return this.http.get<FinancialStats | any>(this.API + '/user/stats/financial-stats');
  }

  searchFinancialStats(date: string): Observable<FinancialStats> {
    return this.http.get<FinancialStats>(this.API + '/user/stats/financial-stats/' + date);
  }

}
