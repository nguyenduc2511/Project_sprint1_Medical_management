import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {EmployeeService} from '../../service/employee.service';
import {PositionService} from '../../service/position.service';
import {Position} from '../../model/position';
// @ts-ignore
import {PageEmployeeDTO} from '../../dto/PageEmployeeDTO';
import {Employee} from '../../model/employee';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  page = 0;
  size = 5;
  nameDelete: string;
  codeDelete: string;
  idDelete: number;
  pageEmployee: any;
  employeeList: Employee[];
  positionList: Position[];
  searchForm: FormGroup;
  pageEmployeeDTO: PageEmployeeDTO;
  errMessage: string;
  constructor(private employeeService: EmployeeService,
              private positionService: PositionService,
              private toastrService: ToastrService) {
    this.searchForm = new FormGroup({
        code: new FormControl(''),
        name: new FormControl(''),
        positionId: new FormControl(''),
        page: new FormControl(this.page),
        size: new FormControl(this.size),
      }
    );
  }

  ngOnInit(): void {
    this.getListPosition();
    this.getListEmployee();
  }
  getListPosition() {
    this.positionService.getListPosition().subscribe(value => {
      this.positionList = value;
    });
  }
  getListEmployee() {
    this.pageEmployeeDTO = this.searchForm.value;
    this.employeeService.getListEmployee(this.pageEmployeeDTO).subscribe(value => {
      this.pageEmployee = value;
      this.employeeList = value.content;
    },
      error => {
      this.employeeList = [];
      this.errMessage = 'Không có dữ liệu.';
      });
  }

  previousPage() {
    this.page--;
    this.searchForm.controls.page.setValue(this.page);
    this.ngOnInit();
  }

  nextPage() {
    this.page++;
    this.searchForm.controls.page.setValue(this.page);
    this.ngOnInit();
  }

  getEmployeeDelete(emp: Employee) {
    this.idDelete = emp.id;
    this.nameDelete = emp.name;
    this.codeDelete = emp.code;
  }

  deleteEmployee() {
    this.employeeService.deleteEmployee(this.idDelete).subscribe(value => {
      this.toastrService.success('Xóa thông tin nhân viên thành công.', 'Tin nhắn từ hệ thống');
      this.ngOnInit();
    });
  }

  searchEmployee() {
    this.page = 0;
    this.searchForm.controls.page.setValue(this.page);
    this.pageEmployeeDTO = this.searchForm.value;
    this.employeeService.getListEmployee(this.pageEmployeeDTO).subscribe(value => {
      this.pageEmployee = value;
      this.employeeList = value.content;
      this.ngOnInit();
    },
      error => {
        this.employeeList = [];
        this.errMessage = 'Không có dữ liệu cần tìm.';
      });
  }
  setPage(pages: string) {
    this.page = Number(pages);
    this.searchForm.controls.page.setValue(this.page);
    this.pageEmployeeDTO = this.searchForm.value;
    this.employeeService.getListEmployee(this.pageEmployeeDTO).subscribe(value => {
        this.errMessage = null;
        this.pageEmployee = value;
        this.employeeList = value.content;
        this.ngOnInit();
      },
      error => {
        this.employeeList = [];
        this.errMessage = 'Không có dữ liệu cần tìm';
      });
  }
}
