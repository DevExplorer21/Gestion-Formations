import { Component, OnInit } from '@angular/core';
import { Domaine } from 'src/app/models/domaine';
import { DomaineService } from 'src/app/services/domaine.service';
@Component({
  selector: 'app-add-domaine',
  templateUrl: './add-domaine.component.html',
  styleUrls: ['./add-domaine.component.css']
})
export class AddDomaineComponent implements OnInit {
  domaine: Domaine = {
    libelle: ''
  };
  submitted = false;
  constructor(private domaineService: DomaineService) { }
  ngOnInit(): void {
  }
  saveDomaine(): void {
    const data = {
      libelle: this.domaine.libelle
    };
    this.domaineService.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }
  newDomaine(): void {
    this.submitted = false;
    this.domaine = {
      libelle: ''
    };
  }
}