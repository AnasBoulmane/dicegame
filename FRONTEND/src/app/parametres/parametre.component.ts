import { Component, OnInit } from '@angular/core';

@Component({
    moduleId: module.id,
    templateUrl: './parametre.component.html'
})
export class ParametreComponent {
    
    ngOnInit() {
        this.active();
    }
    
    active() {
        document.getElementById('welcome').className = '';
        document.getElementById('jeu').className = '';
        document.getElementById('parametres').className = 'current';
        document.getElementById('regles').className = '';
        document.getElementById('meilleurScore').className = '';
    }
}