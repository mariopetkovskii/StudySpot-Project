import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {ApiService} from "../api-service";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-register-component',
  templateUrl: './register-component.component.html',
  styleUrls: ['./register-component.component.scss']
})
export class RegisterComponentComponent implements OnInit{
  addRegisterGroup: FormGroup = new FormGroup({});
  errorRegistration: string = "";
  successRegister: string = "";

  constructor(private formBuilder: FormBuilder,
              private apiService: ApiService,
              private router: Router,
              private translateService: TranslateService){}

  ngOnInit(): void {
    this.addRegisterGroup = this.formBuilder.group({
      'firstName': new FormControl('',[Validators.required]),
      'lastName': new FormControl('',[Validators.required]),
      'email': new FormControl('',[Validators.required]),
      'password': new FormControl('',[Validators.required]),
      'confirmPassword': new FormControl('',[Validators.required])
    });
  }

  onRegisterSubmit(){
    const values = this.addRegisterGroup.value;
    this.apiService.register(values.firstName, values.lastName, values.email, values.password, values.confirmPassword)
      .subscribe(() => {
          this.router.navigate(['/login'])
        },
        (error: any) => {
          console.log(error)
          this.errorRegistration = this.translateService.instant('auth.fail_registration')
        });
  }
}
