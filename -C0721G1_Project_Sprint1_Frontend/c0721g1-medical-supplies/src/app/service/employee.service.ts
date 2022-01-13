import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
// @ts-ignore
import {PageEmployeeDTO} from '../dto/PageEmployeeDTO';
import {Employee} from '../model/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  httpOptions: any;
  private API_URL = 'http://localhost:8080/api';
  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      'Access-Control-Allow-Origin': 'http://localhost:4200',
      'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
    };
  }
  getListEmployee(pageEmployeeDTO: PageEmployeeDTO): Observable<any> {
    const code = pageEmployeeDTO.code;
    const name = pageEmployeeDTO.name;
    const positionId = pageEmployeeDTO.positionId;
    const page = pageEmployeeDTO.page;
    const size = pageEmployeeDTO.size;
    return this.http.get<any>(this.API_URL + '/admin/employee?code=' + code + '&name=' + name + '&positionId='
      + positionId + '&page=' + page + '&size=' + size, this.httpOptions);
  }
  deleteEmployee(id: number): Observable<any> {
    return this.http.delete<void>(this.API_URL + '/admin/employee/' + id, this.httpOptions);
  }

  getAll(): Observable<Employee[] | any> {
    return this.http.get(this.API_URL + '/admin/employee/list', this.httpOptions);
  }

  getCode(): (any) {
    return this.http.get(this.API_URL + '/admin/employee/code', this.httpOptions);
  }

  findById(id: number) {
    return this.http.get(this.API_URL + '/admin/employee/' + id, this.httpOptions);
  }

  createEmployee(employee: Employee) {
    return this.http.post(this.API_URL + '/admin/employee/create', employee, this.httpOptions);
  }

  updateEmployee(employee: Employee) {
    return this.http.patch(this.API_URL + '/admin/employee/update', employee, this.httpOptions);
  }

  findByIdByUser(id: number) {
    return this.http.get(this.API_URL + '/user/employee/detail/' + id, this.httpOptions);
  }

  findByIdEmployeeDetail(id: number): Observable<any> {
    return this.http.get<Employee>(this.API_URL + '/user/employee/edit-detail/' + id, this.httpOptions);
  }

  updateEmployeeDetail(id: number, employee: Employee): Observable<any> {
    return this.http.patch<void>(this.API_URL + '/user/employee/edit-detail/update/' + id, employee, this.httpOptions);
  }
}
