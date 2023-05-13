import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router'; // Import RouterModule

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TranslateLoader, TranslateModule } from "@ngx-translate/core";
import { TranslateHttpLoader } from "@ngx-translate/http-loader";
import { HttpClient, HttpClientModule } from "@angular/common/http";
import { LoginComponentComponent } from './login-component/login-component.component';
import { RegisterComponentComponent } from './register-component/register-component.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { CoursesComponentComponent } from './courses-component/courses-component.component';
import { CourseIdComponentComponent } from './course-id-component/course-id-component.component';
import { CommonModule } from "@angular/common";
import { AddCourseComponentComponent } from './add-course-component/add-course-component.component';
import { LectureIdComponentComponent } from './lecture-id-component/lecture-id-component.component';
import { AddLectureComponentComponent } from './add-lecture-component/add-lecture-component.component';
import { LectureDialogComponentComponent } from './lecture-dialog-component/lecture-dialog-component.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatTableModule} from "@angular/material/table";
import {MatTabsModule} from "@angular/material/tabs";
import { HomeComponentComponent } from './home-component/home-component.component';
import { AboutComponentComponent } from './about-component/about-component.component';
import { QuizComponentComponent } from './quiz-component/quiz-component.component';
import { AddQuizQuestionComponentComponent } from './add-quiz-question-component/add-quiz-question-component.component';
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {BrowserAnimationsModule, NoopAnimationsModule} from "@angular/platform-browser/animations";
import { ProfileComponentComponent } from './profile-component/profile-component.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponentComponent,
    RegisterComponentComponent,
    CoursesComponentComponent,
    CourseIdComponentComponent,
    AddCourseComponentComponent,
    LectureIdComponentComponent,
    AddLectureComponentComponent,
    LectureDialogComponentComponent,
    HomeComponentComponent,
    AboutComponentComponent,
    QuizComponentComponent,
    AddQuizQuestionComponentComponent,
    ProfileComponentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (http: HttpClient) => {
          return new TranslateHttpLoader(http, './assets/i18n/', '.json');
        },
        deps: [HttpClient]
      }
    }),
    ReactiveFormsModule,
    FormsModule,
    CommonModule,
    RouterModule,
    MatDialogModule,
    MatTableModule,
    MatTabsModule,
    MatSnackBarModule,
    BrowserAnimationsModule,
    NoopAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
