import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent implements OnInit {
  constructor(private http: HttpClient) {
  }

  title = 'retroBoard';

  ngOnInit() {
    let headers = new HttpHeaders({});
    this.http.get(`localhost:8080/users`, {
      headers: headers
    }).subscribe(res => {
      console.log(res);
      // data.forEach((item: any) => {
      //   console.log(item)
      // })
    });
  }
}
