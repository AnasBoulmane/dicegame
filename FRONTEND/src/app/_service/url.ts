import { Injectable } from '@angular/core';

@Injectable()
export class Url {
    private url: string;

    constructor() {
        this.url = 'http://localhost:8080/game/';
    }

    getUrl(): string {
        return this.url;
    }
}