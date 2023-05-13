import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pdf',
  templateUrl: './pdf.component.html',
  styleUrls: ['./pdf.component.css']
})
export class PDFComponent implements OnInit {

  URL_Img = 'https://dyl80ryjxr1ke.cloudfront.net/external_assets/'+
  'hero_examples/hair_beach_v1785392215/original.jpeg';

  User_Name = 'Ревякін Олег Олегович';

  Name_Resume = 'Python Developer';

  Skills = 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nam architecto voluptates error ad et eligendi eum, sed quisquam hic porro reprehenderit cum, illo rerum quaerat minima facilis earum voluptas veritatis.'+
  'Lorem ipsum dolor sit, amet consectetur adipisicing elit. Similique aspernatur tempora sapiente laborum! Distinctio illum consequatur omnis autem, praesentium quaerat cupiditate qui cum repellendus, dolorem ad voluptatem eligendi incidunt odit!';

  Experience = 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nam architecto voluptates error ad et eligendi eum, sed quisquam hic porro reprehenderit cum, illo rerum quaerat minima facilis earum voluptas veritatis.'+
  'Lorem ipsum dolor sit, amet consectetur adipisicing elit. Similique aspernatur tempora sapiente laborum! Distinctio illum consequatur omnis autem, praesentium quaerat cupiditate qui cum repellendus, dolorem ad voluptatem eligendi incidunt odit!';

  Languages = 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nam architecto voluptates error ad et eligendi eum, sed quisquam hic porro reprehenderit cum, illo rerum quaerat minima facilis earum voluptas veritatis.'+
  'Lorem ipsum dolor sit, amet consectetur adipisicing elit. Similique aspernatur tempora sapiente laborum! Distinctio illum consequatur omnis autem, praesentium quaerat cupiditate qui cum repellendus, dolorem ad voluptatem eligendi incidunt odit!';

  Description = 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nam architecto voluptates error ad et eligendi eum, sed quisquam hic porro reprehenderit cum, illo rerum quaerat minima facilis earum voluptas veritatis.'+
  'Lorem ipsum dolor sit, amet consectetur adipisicing elit. Similique aspernatur tempora sapiente laborum! Distinctio illum consequatur omnis autem, praesentium quaerat cupiditate qui cum repellendus, dolorem ad voluptatem eligendi incidunt odit!' +
  'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nam architecto voluptates error ad et eligendi eum, sed quisquam hic porro reprehenderit cum, illo rerum quaerat minima facilis earum voluptas veritatis.'+
  'Lorem ipsum dolor sit, amet consectetur adipisicing elit. Similique aspernatur tempora sapiente laborum! Distinctio illum consequatur omnis autem, praesentium quaerat cupiditate qui cum repellendus, dolorem ad voluptatem eligendi incidunt odit!';

  Find_Me = 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nam architecto voluptates error ad et eligendi eum, sed quisquam hic porro reprehenderit cum, illo rerum quaerat minima facilis earum voluptas veritatis.'+
  'Lorem ipsum dolor sit, amet consectetur adipisicing elit. Similique aspernatur tempora sapiente laborum! Distinctio illum consequatur omnis autem, praesentium quaerat cupiditate qui cum repellendus, dolorem ad voluptatem eligendi incidunt odit!';


  constructor() { }

  ngOnInit(): void {
  }

}
