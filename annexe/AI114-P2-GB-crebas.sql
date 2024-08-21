/*==============================================================*/
/* Nom de SGBD :  MySQL 5.0                                     */
/* Date de cr�ation :  24/02/2024 18:03:49                      */
/*==============================================================*/


drop table if exists ADH;

drop table if exists Detail_com;

drop table if exists Plat;

drop table if exists Ville;

drop table if exists adhesion;

drop table if exists colis;

drop table if exists commande;

drop table if exists composition_menu;

drop table if exists disponibilite_livraison;

drop table if exists distribuer;

drop table if exists genre;

drop table if exists jour_semaine;

drop table if exists livreur;

drop table if exists menu;

drop table if exists menu_ouvert_selection;

drop table if exists motif_echec_livraison;

drop table if exists prefere;

drop table if exists regime_alimentaire;

drop table if exists specificite_alimentaire;

drop table if exists tournee;

drop table if exists type_adhesion;

drop table if exists type_plat;

drop table if exists type_repas;

/*==============================================================*/
/* Table : ADH                                                  */
/*==============================================================*/
create table ADH
(
   id_adherent          bigint not null auto_increment,
   id_genre             bigint,
   id_regime_alimentaire bigint,
   ID_ville             bigint,
   Vil_ID_ville         bigint,
   nom                  varchar(254) not null,
   prenom               varchar(254) not null,
   mail                 varchar(254) not null, /* ajout */
   telephone            bigint,
   naissance            datetime,
   rue_livraison        varchar(254),
   information_supplementaire varchar(254),
   adresse_facture      varchar(254),
   mot_de_passe         varchar(254),
   numero_carte_bancaire bigint,
   date_validite_carte_bancaire datetime,
   nom_porteur_carte_bancaire varchar(254),
   primary key (id_adherent)
);

/*==============================================================*/
/* Table : Detail_com                                           */
/*==============================================================*/
create table Detail_com
(
   Id_detail_com        bigint not null auto_increment,
   id_colis             bigint not null,
   id_commande          bigint not null,
   id_plat              bigint,
   id_menu_ouvert_selection bigint,
   Pla_id_plat          bigint,
   Pla_id_plat2         bigint,
   Date_choix           datetime,
   date_validation_choix datetime,
   date_effective_repas datetime,
   Date_de_reglement    datetime,
   date_emission_facture datetime,
   primary key (Id_detail_com)
);

/*==============================================================*/
/* Table : Plat                                                 */
/*==============================================================*/
create table Plat
(
   id_plat              bigint not null auto_increment,
   id_regime_alimentaire bigint,
   id_type_plat         bigint,
   nom_plat             varchar(254),
   description          varchar(254),
   url_image            varchar(254),
   date_entree          datetime,
   date_retrait         datetime,
   primary key (id_plat)
);

/*==============================================================*/
/* Table : Ville                                                */
/*==============================================================*/
create table Ville
(
   ID_ville             bigint not null auto_increment,
   Nom_ville            varchar(254),
   CP_ville             bigint,
   date_integ           datetime,
   date_sortie          datetime,
   primary key (ID_ville)
);

/*==============================================================*/
/* Table : adhesion                                             */
/*==============================================================*/
create table adhesion
(
   id_adhesion          bigint not null auto_increment,
   id_type_adhesion     bigint not null,
   id_adherent          smallint not null,
   debut                datetime,
   fin_prevu            datetime,
   fin_effective        datetime,
   date_paiement        datetime,
   date_emission_facture datetime,
   primary key (id_adhesion)
);

/*==============================================================*/
/* Table : colis                                                */
/*==============================================================*/
create table colis
(
   id_colis             bigint not null auto_increment,
   id_echec_livraison   bigint,
   id_tournee           bigint,
   livraison_prevu      datetime,
   livraison_effective  datetime,
   numero_colis         varchar(254),
   primary key (id_colis)
);

/*==============================================================*/
/* Table : commande                                             */
/*==============================================================*/
create table commande
(
   id_commande          bigint not null auto_increment,
   id_adherent          smallint not null,
   id_menu              bigint,
   validation_com       datetime,
   annulation_com       datetime,
   primary key (id_commande)
);

/*==============================================================*/
/* Table : composition_menu                                     */
/*==============================================================*/
create table composition_menu
(
   id_compo             bigint not null auto_increment,
   id_plat              bigint not null,
   id_menu              bigint not null,
   date_composition     datetime,
   primary key (id_compo)
);

/*==============================================================*/
/* Table : disponibilite_livraison                              */
/*==============================================================*/
create table disponibilite_livraison
(
   id_disponibilite_livraison bigint not null auto_increment,
   id_adherent          bigint,
   id_jour              bigint,
   Date_de_choix        datetime,
   Date_de_retrait      datetime,
   primary key (id_disponibilite_livraison)
);

/*==============================================================*/
/* Table : distribuer                                           */
/*==============================================================*/
create table distribuer
(
   ID_ville             bigint not null auto_increment,
   id_tournee           bigint not null,
   primary key (ID_ville, id_tournee)
);

/*==============================================================*/
/* Table : genre                                                */
/*==============================================================*/
create table genre
(
   id_genre             bigint not null auto_increment,
   libelle_genre        varchar(254),
   primary key (id_genre)
);

/*==============================================================*/
/* Table : jour_semaine                                         */
/*==============================================================*/
create table jour_semaine
(
   id_jour              bigint not null auto_increment,
   libelle_jour         varchar(254),
   primary key (id_jour)
);

/*==============================================================*/
/* Table : livreur                                              */
/*==============================================================*/
create table livreur
(
   id_livreur           bigint not null auto_increment,
   nom                  varchar(254),
   prenom               varchar(254),
   mot_de_passe         varchar(254),
   primary key (id_livreur)
);

/*==============================================================*/
/* Table : menu                                                 */
/*==============================================================*/
create table menu
(
   id_menu              bigint not null auto_increment,
   Caracteristique_type varchar(254),
   nom                  varchar(254),
   creation             datetime,
   retrait              datetime,
   primary key (id_menu)
);

/*==============================================================*/
/* Table : menu_ouvert_selection                                */
/*==============================================================*/
create table menu_ouvert_selection
(
   id_menu_ouvert_selection bigint not null auto_increment,
   id_type_repas        bigint not null,
   id_menu              bigint not null,
   ouverture_selection  datetime,
   fermeture_selection  datetime,
   menu_defaut          bool,
   position_jour        bigint,
   primary key (id_menu_ouvert_selection)
);

/*==============================================================*/
/* Table : motif_echec_livraison                                */
/*==============================================================*/
create table motif_echec_livraison
(
   id_echec_livraison   bigint not null auto_increment,
   motif                varchar(254),
   primary key (id_echec_livraison)
);

/*==============================================================*/
/* Table : prefere                                              */
/*==============================================================*/
create table prefere
(
   id_adherent              bigint not null auto_increment,
   id_specificite_alimentaire bigint not null,
   primary key (id_adherent, id_specificite_alimentaire)
);

/*==============================================================*/
/* Table : regime_alimentaire                                   */
/*==============================================================*/
create table regime_alimentaire
(
   id_regime_alimentaire bigint not null auto_increment,
   nom                  varchar(254),
   debut_prise_en_charge datetime,
   fin_prise_en_charge  datetime,
   primary key (id_regime_alimentaire)
);

/*==============================================================*/
/* Table : specificite_alimentaire                              */
/*==============================================================*/
create table specificite_alimentaire
(
   id_specificite_alimentaire bigint not null auto_increment,
   nom                  varchar(254),
   debut_prise_en_charge datetime,
   fin_prise_en_charge  datetime,
   primary key (id_specificite_alimentaire)
);

/*==============================================================*/
/* Table : tournee                                              */
/*==============================================================*/
create table tournee
(
   id_tournee           bigint not null auto_increment,
   id_livreur           bigint not null,
   numero               varchar(254),
   date                 datetime,
   debut_tournee        datetime,
   fin_tournee          datetime,
   primary key (id_tournee)
);

/*==============================================================*/
/* Table : type_adhesion                                        */
/*==============================================================*/
create table type_adhesion
(
   id_type_adhesion     bigint not null auto_increment,
   typ_id_type_adhesion bigint,
   nom                  varchar(254),
   duree                bigint,
   tarif                bigint,
   date_de_fin_validite datetime,
   primary key (id_type_adhesion)
);

/*==============================================================*/
/* Table : type_plat                                            */
/*==============================================================*/
create table type_plat
(
   id_type_plat         bigint not null auto_increment,
   typ_id_type_plat     bigint,
   type_plat            varchar(254),
   tarif                bigint,
   Date_de_retrait      datetime,
   primary key (id_type_plat)
);

/*==============================================================*/
/* Table : type_repas                                           */
/*==============================================================*/
create table type_repas
(
   id_type_repas        bigint not null auto_increment,
   libelle_repas        national char(1),
   primary key (id_type_repas)
);

alter table ADH add constraint FK_est foreign key (id_genre)
      references genre (id_genre) on delete restrict on update restrict;

alter table ADH add constraint FK_facturer foreign key (Vil_ID_ville)
      references Ville (ID_ville) on delete restrict on update restrict;

alter table ADH add constraint FK_livrer foreign key (ID_ville)
      references Ville (ID_ville) on delete restrict on update restrict;

alter table ADH add constraint FK_restreindre foreign key (id_regime_alimentaire)
      references regime_alimentaire (id_regime_alimentaire) on delete restrict on update restrict;

alter table Detail_com add constraint FK_choisir foreign key (id_menu_ouvert_selection)
      references menu_ouvert_selection (id_menu_ouvert_selection) on delete restrict on update restrict;

alter table Detail_com add constraint FK_comporte foreign key (id_commande)
      references commande (id_commande) on delete restrict on update restrict;

alter table Detail_com add constraint FK_comporter foreign key (id_colis)
      references colis (id_colis) on delete restrict on update restrict;

alter table Detail_com add constraint FK_debuter foreign key (Pla_id_plat2)
      references Plat (id_plat) on delete restrict on update restrict;

alter table Detail_com add constraint FK_finir foreign key (Pla_id_plat)
      references Plat (id_plat) on delete restrict on update restrict;

alter table Detail_com add constraint FK_manger foreign key (id_plat)
      references Plat (id_plat) on delete restrict on update restrict;

alter table Plat add constraint FK_designer foreign key (id_type_plat)
      references type_plat (id_type_plat) on delete restrict on update restrict;

alter table Plat add constraint FK_respecter foreign key (id_regime_alimentaire)
      references regime_alimentaire (id_regime_alimentaire) on delete restrict on update restrict;

alter table adhesion add constraint FK_choisi foreign key (id_adherent)
      references ADH (id_adherent) on delete restrict on update restrict;

alter table adhesion add constraint FK_correspondre foreign key (id_type_adhesion)
      references type_adhesion (id_type_adhesion) on delete restrict on update restrict;

alter table colis add constraint FK_definir foreign key (id_echec_livraison)
      references motif_echec_livraison (id_echec_livraison) on delete restrict on update restrict;

alter table colis add constraint FK_transporter foreign key (id_tournee)
      references tournee (id_tournee) on delete restrict on update restrict;

alter table commande add constraint FK_Association_11 foreign key (id_menu)
      references menu (id_menu) on delete restrict on update restrict;

alter table commande add constraint FK_passer foreign key (id_adherent)
      references ADH (id_adherent) on delete restrict on update restrict;

alter table composition_menu add constraint FK_composer foreign key (id_menu)
      references menu (id_menu) on delete restrict on update restrict;

alter table composition_menu add constraint FK_integrer foreign key (id_plat)
      references Plat (id_plat) on delete restrict on update restrict;

alter table disponibilite_livraison add constraint FK_disponible foreign key (id_adherent)
      references ADH (id_adherent) on delete restrict on update restrict;

alter table disponibilite_livraison add constraint FK_pointer foreign key (id_jour)
      references jour_semaine (id_jour) on delete restrict on update restrict;

alter table distribuer add constraint FK_distribuer foreign key (ID_ville)
      references Ville (ID_ville) on delete restrict on update restrict;

alter table distribuer add constraint FK_distribuer foreign key (id_tournee)
      references tournee (id_tournee) on delete restrict on update restrict;

alter table menu_ouvert_selection add constraint FK_Association_13 foreign key (id_menu)
      references menu (id_menu) on delete restrict on update restrict;

alter table menu_ouvert_selection add constraint FK_correspond foreign key (id_type_repas)
      references type_repas (id_type_repas) on delete restrict on update restrict;

alter table prefere add constraint FK_prefere foreign key (id_adherent)
      references ADH (id_adherent) on delete restrict on update restrict;

alter table prefere add constraint FK_prefere foreign key (id_specificite_alimentaire)
      references specificite_alimentaire (id_specificite_alimentaire) on delete restrict on update restrict;

alter table tournee add constraint FK_livrer foreign key (id_livreur)
      references livreur (id_livreur) on delete restrict on update restrict;

alter table type_adhesion add constraint FK_remplace foreign key (typ_id_type_adhesion)
      references type_adhesion (id_type_adhesion) on delete restrict on update restrict;

alter table type_plat add constraint FK_remplacer foreign key (typ_id_type_plat)
      references type_plat (id_type_plat) on delete restrict on update restrict;

