import {NgModule, OnInit} from '@angular/core';
import {ActivatedRoute, RouterModule, Routes} from '@angular/router';
import {LoginComponentComponent} from "./login-component/login-component.component";
import {RegisterComponentComponent} from "./register-component/register-component.component";
import {CoursesComponentComponent} from "./courses-component/courses-component.component";
import {CourseIdComponentComponent} from "./course-id-component/course-id-component.component";
import {AddCourseComponentComponent} from "./add-course-component/add-course-component.component";
import {AddLectureComponentComponent} from "./add-lecture-component/add-lecture-component.component";
import {HomeComponentComponent} from "./home-component/home-component.component";
import {AboutComponentComponent} from "./about-component/about-component.component";
import {AddQuizQuestionComponentComponent} from "./add-quiz-question-component/add-quiz-question-component.component";
import {QuizComponentComponent} from "./quiz-component/quiz-component.component";
import {ProfileComponentComponent} from "./profile-component/profile-component.component";

const routes: Routes = [
  { path: "login", component: LoginComponentComponent},
  { path: "register", component: RegisterComponentComponent},
  { path: "courses", component: CoursesComponentComponent},
  { path: "course/:id", component: CourseIdComponentComponent },
  { path: "courses/add", component: AddCourseComponentComponent },
  { path: "courses/lecture/add/:id", component: AddLectureComponentComponent },
  { path: "courses/quiz/questions/add/:id", component: AddQuizQuestionComponentComponent },
  { path: "courses/quiz/:id", component: QuizComponentComponent },
  { path: "profile", component: ProfileComponentComponent },
  { path: "about", component: AboutComponentComponent },
  { path: "**", redirectTo: "home" },
  { path: "home", component: HomeComponentComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule{
}
