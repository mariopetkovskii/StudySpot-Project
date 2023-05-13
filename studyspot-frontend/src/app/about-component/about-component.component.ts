import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-about-component',
  templateUrl: './about-component.component.html',
  styleUrls: ['./about-component.component.scss']
})
export class AboutComponentComponent implements OnInit{
  supportLanguages = ['en', 'mk'];

  selectedLanguage: string = ""

  ngOnInit(): void {
    this.selectedLanguage = localStorage.getItem('selectedLanguage_SS') || this.supportLanguages[0];
  }

}
