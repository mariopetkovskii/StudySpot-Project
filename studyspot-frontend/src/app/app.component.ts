import {Component, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import { marker as TRANSLATE_ME } from "@biesbjerg/ngx-translate-extract-marker";
import {Location} from "@angular/common";
import {ApiService} from "./api-service";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  isUserLoggedIn: boolean = false;

  supportLanguages = ['en', 'mk'];

  selectedLanguage: string = localStorage.getItem('selectedLanguage_SS') || this.supportLanguages[0];

  constructor(private translateService: TranslateService,
              private location: Location,
              private apiService: ApiService){
    this.translateService.addLangs(this.supportLanguages);
    this.translateService.setDefaultLang('en');
    const storedLang = localStorage.getItem('selectedLanguage_SS');
    if (storedLang && this.supportLanguages.includes(storedLang)) {
      this.translateService.use(storedLang);
    } else {
      const browserLang = this.translateService.getBrowserLang();
      if (this.supportLanguages.includes(<string>browserLang)) {
        this.translateService.use(<string>browserLang);
      }
    }
  }

  ngOnInit(): void {
    this.isLoggedIn();
  }

  isLoggedIn(){
    this.isUserLoggedIn = !!localStorage.getItem("AUTH_TOKEN_SS");
  }

  logOut(){
    localStorage.removeItem("AUTH_TOKEN_SS")
  }

  useLang(lang: string) {
    this.translateService.use(lang);
    localStorage.setItem('selectedLanguage_SS', lang);
  }

  getUserNameAndSurname(){
    return this.apiService.getUserNameAndSurname();
  }

}
