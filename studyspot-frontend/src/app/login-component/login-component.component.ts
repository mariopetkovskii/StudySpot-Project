import {Component, OnInit} from '@angular/core';
import {ApiService} from "../api-service";
import {ActivatedRoute, Router} from "@angular/router";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.scss']
})
export class LoginComponentComponent implements OnInit{
  email: string = '';
  password: string = '';
  errorMessage: string = '';
  queryMessage: string = '';
  supportLanguages = ['en', 'mk'];

  selectedLanguage: string = "";

  constructor(private apiService: ApiService,
              private router: Router,
              private translateService: TranslateService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.selectedLanguage = localStorage.getItem('selectedLanguage_SS') || this.supportLanguages[0];
    this.route.queryParams.subscribe(params => {
      const message = params['message']; // replace 'message' with your actual query parameter name
      if (message) {
        if(message === 'success'){
          if(this.selectedLanguage === this.supportLanguages[0]){
            this.queryMessage = "Successfully activated account";
          }else{
            this.queryMessage = "Успешно активиран акаунт";
          }
        }else if(message === 'exists'){
          if(this.selectedLanguage === this.supportLanguages[0]){
            this.queryMessage = "Account already activated";
          }else{
            this.queryMessage = "Веќе активиран акаунт";
          }
        }else if(message === 'tokenexpired'){
          if(this.selectedLanguage === this.supportLanguages[0]){
            this.queryMessage = "Token expired, please register again!";
          }else{
            this.queryMessage = "Токенот истече, ве молиме регистрирајте се од почеток!";
          }
        }
        console.log('Message:', message);
      }
    });
  }

  handleSubmit() {
    this.apiService.logIn(this.email, this.password)
      .subscribe((response: any) => {
          localStorage.setItem('AUTH_TOKEN_SS', response.token);
          location.href = "/"
        },
        (error: any) => {
          this.errorMessage = this.translateService.instant('auth.fail_login')
        });
  }

}
