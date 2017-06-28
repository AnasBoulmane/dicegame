import { Component, OnInit } from '@angular/core';

@Component({
  moduleId: module.id,
  templateUrl: './welcome.component.html'
})
export class WelcomeComponent {

ngOnInit() {
        this.active();
    }
    
    active() {
        document.getElementById('welcome').className = 'current';
        document.getElementById('jeu').className = '';
        document.getElementById('parametres').className = '';
        document.getElementById('regles').className = '';
        document.getElementById('meilleurScore').className = '';
    }
}