import {Component, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import { marker as TRANSLATE_ME } from "@biesbjerg/ngx-translate-extract-marker";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  ngOnInit(): void {
    const val = TRANSLATE_ME('home.title');
    console.log(' Title from marker ==> ', TRANSLATE_ME('home.title'));
  }

  supportLanguages = ['en', 'mk'];

  constructor(private translateService: TranslateService){
    this.translateService.addLangs(this.supportLanguages);
    this.translateService.setDefaultLang('en');

    const browserLang = this.translateService.getBrowserLang();

    if (this.supportLanguages.includes(<string>browserLang)) {
      this.translateService.use(<string>browserLang);
    }
  }

  useLang(lang: string) {
    console.log('selected language ==> ', lang);
    this.translateService.use(lang);
  }
}
