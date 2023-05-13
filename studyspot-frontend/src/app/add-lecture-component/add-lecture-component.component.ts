import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../api-service";
import {ActivatedRoute, Router} from "@angular/router";
import {TranslateService} from "@ngx-translate/core";
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-add-lecture-component',
  templateUrl: './add-lecture-component.component.html',
  styleUrls: ['./add-lecture-component.component.scss']
})
export class AddLectureComponentComponent implements OnInit{
  addLectureForm: FormGroup = new FormGroup({});
  errorAddingLecture: string = "";
  courseId: number = 0;

  constructor(private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private apiService: ApiService,
              private router: Router,
              private translateService: TranslateService,
              private snackBar: MatSnackBar){}

  ngOnInit(): void {
    this.courseId = Number(this.route.snapshot.paramMap.get('id'));
    this.addLectureForm = this.formBuilder.group({
      'name': new FormControl('',[Validators.required]),
      'nameMK': new FormControl('',[Validators.required]),
      'description': new FormControl('',[Validators.required]),
      'descriptionMK': new FormControl('',[Validators.required]),
      'URL': new FormControl('',[Validators.required])
    });
  }

  onSubmit(){
    const values = this.addLectureForm.value;
    this.apiService.addLesson(values.name, values.nameMK, values.description, values.descriptionMK, values.URL, this.courseId)
      .subscribe(() => {
          this.router.navigate(['/course/' + this.courseId]),
            this.showSnackbar('Successfully added lecture');

        },
        (error: any) => {
          this.errorAddingLecture = this.translateService.instant('lectures.unsuccessful_add')
        });
  }

  showSnackbar(message: string) {
    this.snackBar.open(message, 'Close', {
      duration: 3000,
      verticalPosition: 'top'
    });
  }
}
