import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../api-service";
import {ActivatedRoute, Router} from "@angular/router";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-add-quiz-question-component',
  templateUrl: './add-quiz-question-component.component.html',
  styleUrls: ['./add-quiz-question-component.component.scss']
})
export class AddQuizQuestionComponentComponent implements OnInit{
  addQuizQuestionFOrm: FormGroup = new FormGroup({});
  errorAdding: string = "";
  courseId: number = 0;

  constructor(private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private apiService: ApiService,
              private router: Router,
              private translateService: TranslateService){}
  ngOnInit(): void {
    this.courseId = Number(this.route.snapshot.paramMap.get('id'));
    this.addQuizQuestionFOrm = this.formBuilder.group({
      'questionText': new FormControl('',[Validators.required]),
      'answer1': new FormControl('',[Validators.required]),
      'answer2': new FormControl('',[Validators.required]),
      'answer3': new FormControl('',[Validators.required]),
      'answer4': new FormControl('',[Validators.required]),
      'correctAnswer': new FormControl('',[Validators.required])
    });
  }

  onSubmit(){
    const values = this.addQuizQuestionFOrm.value;
    this.apiService.addQuizQuestion(values.questionText, values.answer1, values.answer2, values.answer3, values.answer4, values.correctAnswer, this.courseId)
      .subscribe(() => {
          this.router.navigate(['/course/' + this.courseId])
        },
        (error: any) => {
          this.errorAdding = this.translateService.instant('quiz.unsuccessful_add')
        });
  }

}
