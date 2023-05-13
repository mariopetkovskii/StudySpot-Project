import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../api-service";
import {Router} from "@angular/router";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-add-course-component',
  templateUrl: './add-course-component.component.html',
  styleUrls: ['./add-course-component.component.scss']
})
export class AddCourseComponentComponent implements OnInit{
  addCourseForm: FormGroup = new FormGroup({});
  errorAddingCourse: string = "";

  constructor(private formBuilder: FormBuilder,
              private apiService: ApiService,
              private router: Router,
              private translateService: TranslateService){}

  ngOnInit(): void {
    this.addCourseForm = this.formBuilder.group({
      'courseName': new FormControl('',[Validators.required]),
      'description': new FormControl('',[Validators.required]),
      'courseNameMk': new FormControl('',[Validators.required]),
      'descriptionMK': new FormControl('',[Validators.required]),
      'imageUrl': new FormControl('',[Validators.required])
    });
  }

  onSubmit(){
    const values = this.addCourseForm.value;
    this.apiService.addCourse(values.courseName, values.description, values.courseNameMk, values.descriptionMK, values.imageUrl)
      .subscribe(() => {
          this.router.navigate(['/courses'])
        },
        (error: any) => {
          this.errorAddingCourse = this.translateService.instant('courses.unsuccessful_add')
        });
  }
}
