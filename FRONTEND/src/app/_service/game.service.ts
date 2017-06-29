import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';

import { Url } from './url';
import { Resultat } from './../_model/resultat'

@Injectable()
export class GameService {

    private _urlLancer = this.urlRest.getUrl() + 'lancer';
    private _urllogin = this.urlRest.getUrl() + 'login';
    private _urlMeilleurScore = this.urlRest.getUrl() + 'meilleurScore';
    private _urlParametre = this.urlRest.getUrl() + 'parametre';
    private _urlGetParametre = this.urlRest.getUrl() + 'getParametre';

    constructor(private _http: Http, private urlRest: Url) { }

    login(): Observable<Map<string, string>> {
        return this._http.get(this._urllogin)
            .map((response: Response) => <Map<string, string>>response.json())
            .catch(this.handleError);
    }

    lancer(pseudo: string): Observable<Resultat> {
        return this._http.get(this._urlLancer + '?pseudo=' + pseudo)
            .map((response: Response) => <Resultat>response.json())
            .catch(this.handleError);
    }

    getMeilleurScore(): Observable<Map<string, string>> {
        return this._http.get(this._urlMeilleurScore)
            .map((response: Response) => <Map<string, string>>response.json())
            .catch(this.handleError);
    }

    getParametre(): Observable<Map<string, string>> {
        return this._http.get(this._urlGetParametre)
            .map((response: Response) => <Map<string, string>>response.json())
            .catch(this.handleError);
    }

    parametre(nbrTour: number, stockage: number): Observable<Map<string, string>> {
        return this._http.get(this._urlParametre + '?nbrTour=' + nbrTour + '&stockage=' + stockage)
            .map((response: Response) => <Map<string, string>>response.json())
            .catch(this.handleError);
    }

    private handleError(error: Response) {
        console.error('ERROR : ', error);
        return Observable.throw(error.json().error || 'Server error');
    }
}