import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../api-service";
import {TranslateService} from "@ngx-translate/core";


interface Question {
  text: string;
  answer: string;
}

@Component({
  selector: 'app-quiz-component',
  templateUrl: './quiz-component.component.html',
  styleUrls: ['./quiz-component.component.scss']
})
export class QuizComponentComponent implements OnInit{
  quizForm: FormGroup;

  courseId: number = 0;

  questions: any = [];

  constructor(private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private apiService: ApiService,
              private router: Router,
              private translateService: TranslateService){
    this.quizForm = this.formBuilder.group({});

  }
  ngOnInit(): void {
    this.courseId = Number(this.route.snapshot.paramMap.get('id'));

    const formControls = this.questions.map(() => {
      return new FormControl(null);
    });

    this.quizForm = this.formBuilder.group({
      answers: this.formBuilder.array(formControls)
    });
    this.getQuestions();
  }

  getQuestions(){
    this.apiService.getQuestions(Number(this.route.snapshot.paramMap.get('id')))
      .subscribe((data) => {
        this.questions = data;
      })
  }

  onSubmit() {
    const jsonArray: Array<{ questionId: number, answer: string }> = [];

    for (const question of this.questions) {
      const jsonObject = { questionId: question.id, answer: question.answer };
      jsonArray.push(jsonObject);
    }
    this.apiService.submitAnswers(this.courseId, jsonArray)
      .subscribe((data)=> {
        console.log(data)
      })
  }

}
