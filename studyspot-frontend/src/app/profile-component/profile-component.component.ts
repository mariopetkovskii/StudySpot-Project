import {Component, OnInit} from '@angular/core';
import {ApiService} from "../api-service";
import {saveAs} from "file-saver";

@Component({
  selector: 'app-profile-component',
  templateUrl: './profile-component.component.html',
  styleUrls: ['./profile-component.component.scss']
})
export class ProfileComponentComponent implements OnInit{

  user: any;
  userResults: any;

  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
    this.getUserDetails();
    this.getUserQuizResults()
  }

  getUserDetails(){
    this.apiService.getUserDetails().subscribe((data) => {
      this.user = data;
      console.log(data)
    })
  }

  getUserQuizResults(){
    this.apiService.getUserResults().subscribe((data) => {
      this.userResults = data;
      console.log(data)
    })
  }

  getCert(id: number){
    this.apiService.getCert(id).subscribe(blob => {
      // @ts-ignore
      saveAs(blob, "cert.pdf")
    })
  }

}
