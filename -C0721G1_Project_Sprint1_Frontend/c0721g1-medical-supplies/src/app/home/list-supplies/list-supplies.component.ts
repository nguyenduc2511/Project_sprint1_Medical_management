
import {Component, OnInit} from '@angular/core';
import {Supplies} from '../../model/supplies';
import {SuppliesService} from '../../service/supplies.service';
import {Router} from '@angular/router';
import * as AOS from 'aos';

// @ts-ignore
@Component({
  selector: 'app-list-supplies',
  templateUrl: './list-supplies.component.html',
  styleUrls: ['./list-supplies.component.css']
})
export class ListSuppliesComponent implements OnInit {
  suppliesList: Supplies[] = [];
  page = 0;
  totalPage: number;
  errMessage: string;
  id = 0;
  search(id) {
    this.page = 0;
    this.id = id;
    this.ngOnInit();
  }
  getListSupplies() {
    if (this.id === 0) {
      this.suppliesService.getSuppliesList(this.page).subscribe(value => {
        console.log(value);
        this.suppliesList = value.content;
        this.totalPage = value.totalPages;
      }, error => {
        this.suppliesList = [];
        this.errMessage = 'KHÔNG CÓ DỮ LIỆU SẢN PHẨM ĐƯỢC CẬP NHẬP!';
      });
    } else {
      this.suppliesService.searchSupplies(this.page, this.id).subscribe(value => {
        this.totalPage = value.totalPages;
        this.suppliesList = value.content;
      });
    }
  }

  constructor(private suppliesService: SuppliesService,
              private router: Router) {
    window.scrollTo(0, 0);
  }

  ngOnInit(): void {
    this.getListSupplies();
    this.getSuppliesList();
    AOS.init({});
  }

  getSuppliesList() {
        this.suppliesService.findAll().subscribe(value => {
          this.suppliesList = value.content;
      });
}

  nextPage() {
    this.page += 1;
    this.ngOnInit();
  }

  previousPage() {
    this.page -= 1;
    this.ngOnInit();
  }

  lastPage() {
    this.page = this.totalPage - 1;
    this.ngOnInit();
  }

  setPage(pages: string) {
    this.page = Number(pages);
    this.ngOnInit();
  }
}
