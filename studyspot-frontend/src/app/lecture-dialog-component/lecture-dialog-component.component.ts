import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ApiService} from "../api-service";
import {Router} from "@angular/router";
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';


@Component({
  selector: 'app-lecture-dialog-component',
  templateUrl: './lecture-dialog-component.component.html',
  styleUrls: ['./lecture-dialog-component.component.scss'],
  host: {
    '[class.my-dialog-class]': 'true'
  }
})
export class LectureDialogComponentComponent implements OnInit{
  supportLanguages = ['en', 'mk'];

  selectedLanguage: string = ""
  constructor(public dialogRef: MatDialogRef<LectureDialogComponentComponent>,
              private fb: FormBuilder,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private apiService: ApiService,
              private router: Router,
              private sanitizer: DomSanitizer) {
  }
  ngOnInit(): void {
    this.selectedLanguage = localStorage.getItem('selectedLanguage_SS') || this.supportLanguages[0];
  }

  cancelClick() {
    this.dialogRef.close();
  }

  getName(){
    return this.data.lecture.name;
  }

  getNameMK(){
    return this.data.lecture.name_mk;
  }

  getDesc(){
    return this.data.lecture.description;
  }

  getDescMK(){
    return this.data.lecture.description_mk;
  }

  getURL(){
    const sanitizedUrl: SafeResourceUrl = this.sanitizer.bypassSecurityTrustResourceUrl(this.data.lecture.url);
    return sanitizedUrl;
  }

}
