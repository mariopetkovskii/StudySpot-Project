import {Component, OnInit} from '@angular/core';
import {ApiService} from "../api-service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-courses-component',
  templateUrl: './courses-component.component.html',
  styleUrls: ['./courses-component.component.scss']
})
export class CoursesComponentComponent implements OnInit{

  allCourses: any = [];

  supportLanguages = ['en', 'mk'];

  selectedLanguage: string = ""

  constructor(private apiService: ApiService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getCourses();
    this.selectedLanguage = localStorage.getItem('selectedLanguage_SS') || this.supportLanguages[0];
    this.apiService.isUserAdmin();
  }

  getCourses(){
    this.apiService.getCourses()
      .subscribe((data) => {
        this.allCourses = data;
      })
  }

  openCourse(id: number){
    this.router.navigate(['course/' + id])
  }

  isAdminAuthority(): boolean{
    return this.apiService.isUserAdmin();
  }

  isUserLoggedIn(): boolean{
    return this.apiService.isUserLoggedIn();
  }
}
