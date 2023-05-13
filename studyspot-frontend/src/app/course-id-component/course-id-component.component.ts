import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ApiService} from "../api-service";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {LectureDialogComponentComponent} from "../lecture-dialog-component/lecture-dialog-component.component";


interface Lesson {
  id: number;
  name: string;
  name_mk: string | null;
  description: string;
  description_mk: string | null;
  url: string;
}

@Component({
  selector: 'app-course-id-component',
  templateUrl: './course-id-component.component.html',
  styleUrls: ['./course-id-component.component.scss']
})
export class CourseIdComponentComponent implements OnInit{
  courseId: number = 0;
  course: any;

  supportLanguages = ['en', 'mk'];

  selectedLanguage: string = ""
  constructor(private route: ActivatedRoute,
              private apiService: ApiService,
              private router: Router,
              private dialog: MatDialog) { }

  ngOnInit(): void {
    this.selectedLanguage = localStorage.getItem('selectedLanguage_SS') || this.supportLanguages[0];
    this.courseId = Number(this.route.snapshot.paramMap.get('id'));
    this.getCourseById();
  }

  getCourseById(){
    this.apiService.getCourseById(this.courseId)
      .subscribe((data) => {
        console.log(data)
        this.course = data;
      })
  }

  addLecture(){
    this.router.navigate(['courses/lecture/add/' + this.courseId])
  }

  addQuestionQuiz(){
    this.router.navigate(['courses/quiz/questions/add/' + this.courseId])
  }

  startQuiz(){
    this.router.navigate(['courses/quiz/' + this.courseId])
  }

  showInfo(){
    console.log(this.course)
  }

  openLecture(id: number){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '50%';
    dialogConfig.panelClass = 'my-dialog-class';
    const lesson: Lesson | undefined = this.course.lessons.find((lesson: Lesson) => lesson.id === id);
    const payload = {
      lecture: lesson
    }
    dialogConfig.data = payload;
    const dialogRef = this.dialog.open(LectureDialogComponentComponent, dialogConfig);

  }

  isAdminAuthority(): boolean{
    return this.apiService.isUserAdmin();
  }
}
