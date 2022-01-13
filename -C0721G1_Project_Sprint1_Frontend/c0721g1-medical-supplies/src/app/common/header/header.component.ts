import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
    document.querySelector('.infor_link-toggle')
      .addEventListener('click', classToggle);
  }

}

function classToggle() {
  const navs = document.querySelectorAll('.infor_Items');

  navs.forEach(nav => nav.classList.toggle('infor_ToggleShow'));
}



