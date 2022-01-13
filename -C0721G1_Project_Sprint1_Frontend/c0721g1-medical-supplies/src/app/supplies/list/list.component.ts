import {Component, OnInit} from '@angular/core';
import {Producer} from '../../model/producer';
import {Supplies} from '../../model/supplies';
import {SuppliesType} from '../../model/supplies-type';
import {FormControl, FormGroup} from '@angular/forms';
// @ts-ignore
import {PageSuppliesDTO} from '../../dto/PageSuppliesDTO';
import {SuppliesService} from '../../service/supplies.service';
import {SuppliesTypeService} from '../../service/supplies-type.service';
import {ProducerService} from '../../service/producer.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  page = 0;
  size = 5;
  pageSupplies: any;
  suppliesList: Supplies[];
  producerList: Producer[];
  suppliesTypeList: SuppliesType[];
  searchForm: FormGroup;
  pageSuppliesDTO: PageSuppliesDTO;
  idDel: number;
  nameDel: string;
  codeDel: string;
  errMessage: string;

  constructor(private suppliesService: SuppliesService,
              private suppliesTypeService: SuppliesTypeService,
              private producerService: ProducerService,
              private toastrService: ToastrService) {
    this.searchForm = new FormGroup({
        code: new FormControl(''),
        name: new FormControl(''),
        suppliesType: new FormControl(''),
        page: new FormControl(this.page),
        size: new FormControl(this.size),
      }
    );
  }


  ngOnInit(): void {
    this.getListSuppliesType();
    this.getListProducer();
    this.getListSupplies();
  }

  private getListSupplies() {
    this.pageSuppliesDTO = this.searchForm.value;
    this.suppliesService.getListSupplies(this.pageSuppliesDTO).subscribe(value => {
      this.pageSupplies = value;
      this.suppliesList = value.content;
      // tslint:disable-next-line:prefer-for-of
      for (let i = 0; i < this.suppliesList.length; i++) {
        // tslint:disable-next-line:prefer-for-of
        for (let j = 0; j < this.suppliesTypeList.length; j++) {
          if (this.suppliesList[i].supplies_type_id === this.suppliesTypeList[j].id) {
            this.suppliesList[i].suppliesType = this.suppliesTypeList[j];
            break;
          }
        }
        // tslint:disable-next-line:prefer-for-of
        for (let j = 0; j < this.producerList.length; j++) {
          if (this.suppliesList[i].producer_id === this.producerList[j].id) {
            this.suppliesList[i].producer = this.producerList[j];
            break;
          }
        }
      }
    });
  }

  private getListSuppliesType() {
    this.suppliesTypeService.getListSuppliesType().subscribe(value => {
      this.suppliesTypeList = value;
    });
  }

  private getListProducer() {
    this.producerService.getListProducer().subscribe(value => {
      this.producerList = value;
    });
  }

  getSuppliesDelete(sup: Supplies) {
    this.idDel = sup.id;
    this.nameDel = sup.name;
    this.codeDel = sup.code;

  }

  deleteSupplies() {
    this.suppliesService.deleteSupplies(this.idDel).subscribe(value => {
      this.toastrService.success('Xóa thông tin vật tư thành công', 'Tin nhắn từ hệ thống');
      this.ngOnInit();
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

  searchSupplies() {
    this.page = 0;
    this.searchForm.controls.page.setValue(this.page);
    this.pageSuppliesDTO = this.searchForm.value;
    this.suppliesService.getListSupplies(this.pageSuppliesDTO).subscribe(value => {
        this.errMessage = null;
        this.pageSupplies = value;
        this.suppliesList = value.content;
        this.ngOnInit();
      },
      error => {
        this.suppliesList = [];
        this.errMessage = 'Không có dữ liệu cần tìm';
      }
    );
  }
  setPage(pages: string) {
    this.page = Number(pages);
    this.searchForm.controls.page.setValue(this.page);
    this.pageSuppliesDTO = this.searchForm.value;
    this.suppliesService.getListSupplies(this.pageSuppliesDTO).subscribe(value => {
        this.errMessage = null;
        this.pageSupplies = value;
        this.suppliesList = value.content;
        this.ngOnInit();
      },
      error => {
        this.suppliesList = [];
        this.errMessage = 'Không có dữ liệu cần tìm';
      }
    );
  }
}
