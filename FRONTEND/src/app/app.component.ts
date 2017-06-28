import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  active(element: string) {
        document.getElementById('welcome').className = '';
        document.getElementById('jeu').className = '';
        document.getElementById('parametres').className = '';
        document.getElementById('regles').className = '';
        document.getElementById('meilleurScore').className = '';
        document.getElementById(element).className = 'current';
    }
}
