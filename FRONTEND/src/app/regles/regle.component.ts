import { Component, OnInit } from '@angular/core';

@Component({
    moduleId: module.id,
    templateUrl: './regle.component.html'
})
export class RegleComponent {

    ngOnInit() {
        this.active();
    }
    
    active() {
        document.getElementById('welcome').className = '';
        document.getElementById('jeu').className = '';
        document.getElementById('parametres').className = '';
        document.getElementById('regles').className = 'current';
        document.getElementById('meilleurScore').className = '';
    }

}