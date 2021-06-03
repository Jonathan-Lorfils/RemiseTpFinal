# Phase1

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 11.1.4.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.

## Librairies installer

npm i bootstrap --save
npm i jquery --save
npm i @fortawesome/fontawesome-free --save

## Add tp app.module

HttpClientModule -> import 
FormModule
ReactiveFormsModule

## Configuration libraires

 "styles": [
              "node_modules/bootstrap/dist/css/bootstrap.min.css",
              "node_modules/@fortawesome/fontawesome-free/css/all.min.css",
              "src/styles.css"
            ],
            "scripts": ["node_modules/jquery/dist/jquery.min.js",
              "node_modules/bootstrap/dist/js/bootstra.min.js"]


# Add to app.module

HttpClientModule => import {HttpClientModule} from '@angular/common/http';