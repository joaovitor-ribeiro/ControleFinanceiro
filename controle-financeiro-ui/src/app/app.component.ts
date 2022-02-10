import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'controle-financeiro-ui';

  constructor(private router : Router) {}

  isSelected(url: string) {
    return this.router.url.includes(url);
  }
}
