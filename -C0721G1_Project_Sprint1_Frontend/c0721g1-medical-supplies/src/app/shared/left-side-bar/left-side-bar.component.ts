import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../../service/token-storage.service';

@Component({
  selector: 'app-left-side-bar',
  templateUrl: './left-side-bar.component.html',
  styleUrls: ['./left-side-bar.component.css']
})
export class LeftSideBarComponent implements OnInit {

  id: number;

  constructor(private tokenStorageService: TokenStorageService) {
    this.id = this.tokenStorageService.getUser().employee.id;
  }

  ngOnInit(): void {
  }

}
