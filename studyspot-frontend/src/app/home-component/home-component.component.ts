import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-home-component',
  templateUrl: './home-component.component.html',
  styleUrls: ['./home-component.component.scss']
})
export class HomeComponentComponent implements OnInit{

  supportLanguages = ['en', 'mk'];

  selectedLanguage: string = ""

  ngOnInit(): void {
    this.selectedLanguage = localStorage.getItem('selectedLanguage_SS') || this.supportLanguages[0];
  }

}
