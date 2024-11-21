import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Domaine } from 'src/app/models/domaine';
import { DomaineService } from 'src/app/services/domaine.service';

@Component({
    selector: 'app-list-domaine',
    templateUrl: './list-domaine.component.html',
    styleUrls: ['./list-domaine.component.css']
})

export class ListDomaineComponent implements OnInit {
  domaines?: Domaine[];
  currentDomaine: Domaine = {};
  currentIndex = -1;
  libelle = '';

  constructor(private domaineService: DomaineService, private router : Router) { }
  
  ngOnInit(): void {
    this.retrieveDomaines();
  }

  retrieveDomaines(): void {
    this.domaineService.getAll()
      .subscribe({
        next: (data) => {
          this.domaines = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  refreshList(): void {
    this.retrieveDomaines();
    this.currentDomaine = {};
    this.currentIndex = -1;
  }

  setActiveDomaine(domaine: Domaine, index: number): void {
    this.currentDomaine = domaine;
    this.currentIndex = index;
  }

  removeAllDomaines(): void {
    this.domaineService.deleteAll()
      .subscribe({
        next: (res) => {
          console.log(res);
          this.refreshList();
        },
        error: (e) => console.error(e)
      });
  }
  
  addDomaines(){
    this.router.navigateByUrl('/adddomaine');
  }

  searchLibelle(): void {
    this.currentDomaine = {};
    this.currentIndex = -1;
    this.domaineService.findByLibelle(this.libelle)
      .subscribe({
        next: (data) => {
          this.domaines = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
}