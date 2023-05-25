import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { first, map } from 'rxjs';
import { saveAs } from 'file-saver';
import {environment} from "../environments/environment.prod";


@Injectable({
  providedIn: 'root'
})
export class ApiService {

  endpointUrl: string = 'http://51.142.170.211:8080/rest';

  // endpointUrl: string = environment.apiUrl
  constructor(private httpClient: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('AUTH_TOKEN_SS');
      return new HttpHeaders({
        'Authorization': 'Bearer ' + token});
  }

  isUserAdmin(): boolean {
    const token = localStorage.getItem('AUTH_TOKEN_SS');
    if (token) {
      const tokenPayload = this.decodeToken(token);

      if (tokenPayload && tokenPayload.authority) {
        return tokenPayload.authority.includes('ROLE_ADMIN');
      }
    }
    return false;
  }

  isUserLoggedIn(): boolean{
    const token = localStorage.getItem('AUTH_TOKEN_SS');
    if (token) {
      const tokenPayload = this.decodeToken(token);

      if (tokenPayload && tokenPayload.authority) {
        return tokenPayload.authority.includes('ROLE_USER') || tokenPayload.authority.includes('ROLE_ADMIN');
      }
    }
    return false;
  }

  getUserNameAndSurname(): string{
    const token = localStorage.getItem('AUTH_TOKEN_SS');
    if (token) {
      const tokenPayload = this.decodeToken(token);

      if (tokenPayload && tokenPayload.firstName && tokenPayload.lastName) {
        return tokenPayload.firstName + " " + tokenPayload.lastName;
      }
    }
    return "My profile"
  }

  private decodeToken(token: string): any {
    const tokenParts = token.split('.');
    if (tokenParts.length === 3) {
      const tokenPayload = JSON.parse(atob(tokenParts[1])); // Decode the base64-encoded token payload
      return tokenPayload;
    }
    return null; // Return null if the token is invalid
  }

  logIn(email: string, password: string){
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'text/plain',
        'Authorization': 'Basic ' + btoa(email + ':' + password)
      })
    };

    return this.httpClient.post(this.endpointUrl + "/user/login", {}, httpOptions)
  }

  register(firstName: string, lastName: string, email: string, password: string, confirmPassword: string ){
    let json = {
      "firstName": firstName,
      "lastName": lastName,
      "email": email,
      "password": password,
      "confirmPassword": confirmPassword
    }
    return this.httpClient.post(this.endpointUrl + "/user/register", json);
  }

  getCourses(){
    return this.httpClient.get(this.endpointUrl + "/course/get-courses", {
      headers: this.getAuthHeaders()
    });
  }

  getCourseById(id: number){
    return this.httpClient.get(this.endpointUrl + "/course/get-course/" + id, {
      headers: this.getAuthHeaders()
    });
  }

  addCourse(name: string, desc: string, name_mk: string, desc_mk:string, image:string){
    let json = {
      "name": name,
      "name_mk": name_mk,
      "imageUrl": image,
      "desc": desc,
      "desc_mk": desc_mk
    }

    const httpOptions = {
      headers: this.getAuthHeaders()
    };

    return this.httpClient.post(this.endpointUrl + "/course/add", json, httpOptions);
  }

  addLesson(name: string, nameMK:string, description:string, descriptionMK:string, URL:string, courseId:number){
    let json = {
      "name": name,
      "url": URL,
      "desc": description,
      "name_mk": nameMK,
      "description_mk": descriptionMK,
      "courseId": courseId
    }

    const httpOptions = {
      headers: this.getAuthHeaders()
    };

    return this.httpClient.post(this.endpointUrl + "/course/lesson-add", json, httpOptions);
  }

  addQuizQuestion(questionText: string, answer1:string, answer2:string, answer3:string, answer4:string, correctAnswer:number, courseId:number){
    let json = {
      "questionText": questionText,
      "answer1": answer1,
      "answer2": answer2,
      "answer3": answer3,
      "answer4": answer4,
      "correctAnswer": correctAnswer,
      "courseId": courseId
    }

    const httpOptions = {
      headers: this.getAuthHeaders()
    };

    return this.httpClient.post(this.endpointUrl + "/course/quiz-question-add", json, httpOptions);
  }

  getQuestions(courseId: number){
    console.log(courseId)
    let json = {
      "id": courseId
    }

    const httpOptions = {
      headers: this.getAuthHeaders()
    };

    return this.httpClient.post(this.endpointUrl + "/course/get-quiz", json, httpOptions);
  }

  submitAnswers(courseId: number, answers: Array<{ questionId: number, answer: string }>){
    let json = {
      "courseId": courseId,
      "questions": answers
    }

    const httpOptions = {
      headers: this.getAuthHeaders()
    };

    return this.httpClient.post(this.endpointUrl + "/course/send-answers", json, httpOptions)
  }

  getUserDetails(){
    const httpOptions = {
      headers: this.getAuthHeaders()
    };

    return this.httpClient.post(this.endpointUrl + "/user/details", {}, httpOptions);
  }

  getUserResults(){
    const httpOptions = {
      headers: this.getAuthHeaders()
    };

    return this.httpClient.post(this.endpointUrl + "/user/get/quiz/results", {}, httpOptions);
  }

  getCert(id: number){
    const httpOptions = {
      headers: this.getAuthHeaders(),
      responseType: 'blob'
    };

    let json = {
      "id": id
    }
    // @ts-ignore
    return this.httpClient.post(this.endpointUrl + "/course/get-cert", json, httpOptions)
  }

  deleteLecture(id: number){
    const httpOptions = {
      headers: this.getAuthHeaders(),
      responseType: 'blob'
    };

    // @ts-ignore
    return this.httpClient.delete(this.endpointUrl + "/lesson/delete-lesson/" + id, httpOptions)
  }

}
