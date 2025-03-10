PGDMP     0                    x           RecreationCenter    12.2    12.2 a    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    24792    RecreationCenter    DATABASE     �   CREATE DATABASE "RecreationCenter" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
 "   DROP DATABASE "RecreationCenter";
                postgres    false            �            1255    24793    AutoUpdateInfoRoomNumber()    FUNCTION     �   CREATE FUNCTION public."AutoUpdateInfoRoomNumber"() RETURNS trigger
    LANGUAGE plpgsql
    AS $$BEGIN
	UPDATE public."RoomNumber" SET "idRoomStatus"=4 WHERE id = new."idRoomNumber";
	RETURN NEW;
END;$$;
 3   DROP FUNCTION public."AutoUpdateInfoRoomNumber"();
       public          postgres    false            �            1255    24794 !   DeleteAdditionalServices(integer)    FUNCTION     �   CREATE FUNCTION public."DeleteAdditionalServices"("DelID" integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	DELETE FROM public."AdditionalServices"
		WHERE id = "DelID";
END$$;
 B   DROP FUNCTION public."DeleteAdditionalServices"("DelID" integer);
       public          postgres    false            �            1255    24795    DeleteClient(integer)    FUNCTION     �   CREATE FUNCTION public."DeleteClient"("DelID" integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	DELETE FROM public."Client"
		WHERE id = "DelID";
END$$;
 6   DROP FUNCTION public."DeleteClient"("DelID" integer);
       public          postgres    false            �            1255    24796    DeleteComfort(integer)    FUNCTION     �   CREATE FUNCTION public."DeleteComfort"("DelID" integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	DELETE FROM public."Comfort"
		WHERE id = "DelID";
END$$;
 7   DROP FUNCTION public."DeleteComfort"("DelID" integer);
       public          postgres    false            �            1255    24797    DeleteFood(integer)    FUNCTION     �   CREATE FUNCTION public."DeleteFood"("DelID" integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	DELETE FROM public."Food"
		WHERE id = "DelID";
END$$;
 4   DROP FUNCTION public."DeleteFood"("DelID" integer);
       public          postgres    false            �            1255    24798    DeleteRecreationBase(integer)    FUNCTION     �   CREATE FUNCTION public."DeleteRecreationBase"("DelID" integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	DELETE FROM public."RecreationBase"
		WHERE id = "DelID";
END$$;
 >   DROP FUNCTION public."DeleteRecreationBase"("DelID" integer);
       public          postgres    false            �            1255    24799    DeleteRoomNumber(integer)    FUNCTION     �   CREATE FUNCTION public."DeleteRoomNumber"("DelID" integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	DELETE FROM public."RoomNumber"
		WHERE id = "DelID";
END$$;
 :   DROP FUNCTION public."DeleteRoomNumber"("DelID" integer);
       public          postgres    false            �            1255    24800    DeleteRoomStatus(integer)    FUNCTION     �   CREATE FUNCTION public."DeleteRoomStatus"("DelID" integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	DELETE FROM public."RoomStatus"
		WHERE id = "DelID";
END$$;
 :   DROP FUNCTION public."DeleteRoomStatus"("DelID" integer);
       public          postgres    false            �            1255    24801    DeleteSpaTreatments(integer)    FUNCTION     �   CREATE FUNCTION public."DeleteSpaTreatments"("DelID" integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	DELETE FROM public."SpaTreatments"
		WHERE id = "DelID";
END$$;
 =   DROP FUNCTION public."DeleteSpaTreatments"("DelID" integer);
       public          postgres    false            �            1255    24802 *   InsertAdditionalServices(integer, integer)    FUNCTION       CREATE FUNCTION public."InsertAdditionalServices"("FoodID" integer, "SpaTreatmentsID" integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	INSERT INTO public."AdditionalServices"("idFood", "idSpaTreatments")
		VALUES ("FoodID", "SpaTreatmentsID");
END$$;
 ^   DROP FUNCTION public."InsertAdditionalServices"("FoodID" integer, "SpaTreatmentsID" integer);
       public          postgres    false            �            1255    24803 $   InsertClient(text, text, text, text)    FUNCTION     (  CREATE FUNCTION public."InsertClient"("SurNameSTR" text, "NameSTR" text, "PatronymicSTR" text, "PhoneSTR" text) RETURNS void
    LANGUAGE plpgsql
    AS $$begin
	INSERT INTO public."Client"(surname, name, patronymic, phone)
		VALUES ("SurNameSTR", "NameSTR", "PatronymicSTR", "PhoneSTR");
end$$;
 o   DROP FUNCTION public."InsertClient"("SurNameSTR" text, "NameSTR" text, "PatronymicSTR" text, "PhoneSTR" text);
       public          postgres    false            �            1255    24804    InsertComfort(text)    FUNCTION     �   CREATE FUNCTION public."InsertComfort"("TypeSTR" text) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	INSERT INTO public."Comfort"(type)
		VALUES ("TypeSTR");
END$$;
 6   DROP FUNCTION public."InsertComfort"("TypeSTR" text);
       public          postgres    false            �            1255    24805    InsertFood(text)    FUNCTION     �   CREATE FUNCTION public."InsertFood"("TypeSTR" text) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	INSERT INTO public."Food"(type)
		VALUES ("TypeSTR");
END$$;
 3   DROP FUNCTION public."InsertFood"("TypeSTR" text);
       public          postgres    false            �            1255    24806 ;   InsertRecreationBase(integer, integer, date, date, integer)    FUNCTION     �  CREATE FUNCTION public."InsertRecreationBase"("ClientID" integer, "RoomNumberID" integer, "DateSTART" date, "DateEND" date, "AdditionalServicesID" integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	INSERT INTO public."RecreationBase"("idClient", "idRoomNumber", "startDate", "endDate", "idAdditionalServices") 
		VALUES("ClientID", "RoomNumberID", "DateSTART", "DateEND", "AdditionalServicesID");
END$$;
 �   DROP FUNCTION public."InsertRecreationBase"("ClientID" integer, "RoomNumberID" integer, "DateSTART" date, "DateEND" date, "AdditionalServicesID" integer);
       public          postgres    false            �            1255    24807 =   InsertRoomNumber(integer, integer, integer, integer, integer)    FUNCTION     o  CREATE FUNCTION public."InsertRoomNumber"("Room" integer, "NumSeats" integer, "PriceD" integer, "ComfortID" integer, "RoomStatusID" integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	INSERT INTO public."RoomNumber"("roomNumber", "numberOfSeats", price, "idComfort", "idRoomStatus")
		VALUES ("Room", "NumSeats", "PriceD", "ComfortID", "RoomStatusID");
END$$;
 �   DROP FUNCTION public."InsertRoomNumber"("Room" integer, "NumSeats" integer, "PriceD" integer, "ComfortID" integer, "RoomStatusID" integer);
       public          postgres    false            �            1255    24808    InsertRoomStatus(text)    FUNCTION     �   CREATE FUNCTION public."InsertRoomStatus"("StatusSTR" text) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	INSERT INTO public."RoomStatus"(status)
		VALUES ("StatusSTR");
END$$;
 ;   DROP FUNCTION public."InsertRoomStatus"("StatusSTR" text);
       public          postgres    false            �            1255    24809    InsertSpaTreatments(text)    FUNCTION     �   CREATE FUNCTION public."InsertSpaTreatments"("TypeSTR" text) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	INSERT INTO public."SpaTreatments"("typeSt")
		VALUES ("TypeSTR");
END$$;
 <   DROP FUNCTION public."InsertSpaTreatments"("TypeSTR" text);
       public          postgres    false                       1255    24810    NumberOfFreePlaces()    FUNCTION     �   CREATE FUNCTION public."NumberOfFreePlaces"() RETURNS integer
    LANGUAGE plpgsql
    AS $$DECLARE 
	Num INTEGER;
BEGIN
	select count("idRoomStatus") into Num FROM public."RoomNumber" where "idRoomStatus"=5;
	return Num;
END$$;
 -   DROP FUNCTION public."NumberOfFreePlaces"();
       public          postgres    false            �            1255    24811 3   UpdateAdditionalServices(integer, integer, integer)    FUNCTION        CREATE FUNCTION public."UpdateAdditionalServices"("FoodID" integer, "SpaTreatmentsID" integer, "WhereID" integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	UPDATE public."AdditionalServices"
	SET "idFood"="FoodID", "idSpaTreatments"="SpaTreatmentsID" WHERE id = "WhereID";
END$$;
 q   DROP FUNCTION public."UpdateAdditionalServices"("FoodID" integer, "SpaTreatmentsID" integer, "WhereID" integer);
       public          postgres    false            �            1255    24812 -   UpdateClient(text, text, text, text, integer)    FUNCTION     B  CREATE FUNCTION public."UpdateClient"("SurNameSTR" text, "NameSTR" text, "PatronymicSTR" text, "PhoneSTR" text, "WhereID" integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	UPDATE public."Client"
		SET surname="SurNameSTR", name="NameSTR", patronymic="PatronymicSTR", phone="PhoneSTR" WHERE id = "WhereID";
END$$;
 �   DROP FUNCTION public."UpdateClient"("SurNameSTR" text, "NameSTR" text, "PatronymicSTR" text, "PhoneSTR" text, "WhereID" integer);
       public          postgres    false            �            1255    24813    UpdateComfort(text, integer)    FUNCTION     �   CREATE FUNCTION public."UpdateComfort"("TypeSTR" text, "WhereID" integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	UPDATE public."Comfort"
		SET type="TypeSTR" WHERE id = "WhereID";
END$$;
 I   DROP FUNCTION public."UpdateComfort"("TypeSTR" text, "WhereID" integer);
       public          postgres    false            �            1255    24814    UpdateFood(text, integer)    FUNCTION     �   CREATE FUNCTION public."UpdateFood"("TypeSTR" text, "WhereID" integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	UPDATE public."Food"
		SET type="TypeSTR" WHERE id = "WhereID";
END$$;
 F   DROP FUNCTION public."UpdateFood"("TypeSTR" text, "WhereID" integer);
       public          postgres    false            �            1255    24815 D   UpdateRecreationBase(integer, integer, date, date, integer, integer)    FUNCTION     �  CREATE FUNCTION public."UpdateRecreationBase"("ClientID" integer, "RoomNumberID" integer, "DateSTART" date, "DateEND" date, "AdditionalServicesID" integer, "WhereID" integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	UPDATE public."RecreationBase" SET "idClient" = "ClientID", 
		"idRoomNumber" = "RoomNumberID", "startDate" = "DateSTART", 
		"endDate" = "DateEND", "idAdditionalServices" = "AdditionalServicesID" 
			WHERE id = "WhereID";
END$$;
 �   DROP FUNCTION public."UpdateRecreationBase"("ClientID" integer, "RoomNumberID" integer, "DateSTART" date, "DateEND" date, "AdditionalServicesID" integer, "WhereID" integer);
       public          postgres    false            �            1255    24816 F   UpdateRoomNumber(integer, integer, integer, integer, integer, integer)    FUNCTION     �  CREATE FUNCTION public."UpdateRoomNumber"("Room" integer, "NumSeats" integer, "PriceD" integer, "ComfortID" integer, "RoomStatusID" integer, "WhereID" integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	UPDATE public."RoomNumber"
	SET "roomNumber"="Room", "numberOfSeats"="NumSeats", price="PriceD", "idComfort"="ComfortID", "idRoomStatus"="RoomStatusID" WHERE id = "WhereID";
END$$;
 �   DROP FUNCTION public."UpdateRoomNumber"("Room" integer, "NumSeats" integer, "PriceD" integer, "ComfortID" integer, "RoomStatusID" integer, "WhereID" integer);
       public          postgres    false            �            1255    24817    UpdateRoomStatus(text, integer)    FUNCTION     �   CREATE FUNCTION public."UpdateRoomStatus"("StatusSTR" text, "WhereID" integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	UPDATE public."RoomStatus"
		SET status="StatusSTR" WHERE id="WhereID";
END$$;
 N   DROP FUNCTION public."UpdateRoomStatus"("StatusSTR" text, "WhereID" integer);
       public          postgres    false            �            1255    24818 "   UpdateSpaTreatments(text, integer)    FUNCTION     �   CREATE FUNCTION public."UpdateSpaTreatments"("TypeSTR" text, "WhereID" integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
	UPDATE public."SpaTreatments"
		SET "typeSt"="TypeSTR" WHERE id = "WhereID";
END$$;
 O   DROP FUNCTION public."UpdateSpaTreatments"("TypeSTR" text, "WhereID" integer);
       public          postgres    false            �            1259    24819    AdditionalServices    TABLE     �   CREATE TABLE public."AdditionalServices" (
    id integer NOT NULL,
    "idFood" integer NOT NULL,
    "idSpaTreatments" integer NOT NULL
);
 (   DROP TABLE public."AdditionalServices";
       public         heap    postgres    false            �            1259    24822    Food    TABLE     G   CREATE TABLE public."Food" (
    id integer NOT NULL,
    type text
);
    DROP TABLE public."Food";
       public         heap    postgres    false            �            1259    24828    SpaTreatments    TABLE     T   CREATE TABLE public."SpaTreatments" (
    id integer NOT NULL,
    "typeSt" text
);
 #   DROP TABLE public."SpaTreatments";
       public         heap    postgres    false            �            1259    24834    AdditionalServicesView    VIEW       CREATE VIEW public."AdditionalServicesView" AS
 SELECT aserv.id,
    f.type,
    st."typeSt"
   FROM public."AdditionalServices" aserv,
    public."Food" f,
    public."SpaTreatments" st
  WHERE ((f.id = aserv."idFood") AND (st.id = aserv."idSpaTreatments"));
 +   DROP VIEW public."AdditionalServicesView";
       public          postgres    false    204    204    203    203    202    202    202            �            1259    24838    AdditionalServices_id_seq    SEQUENCE     �   CREATE SEQUENCE public."AdditionalServices_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public."AdditionalServices_id_seq";
       public          postgres    false    202            �           0    0    AdditionalServices_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public."AdditionalServices_id_seq" OWNED BY public."AdditionalServices".id;
          public          postgres    false    206            �            1259    24840    Client    TABLE     �   CREATE TABLE public."Client" (
    id integer NOT NULL,
    surname text,
    name text,
    patronymic text,
    phone text
);
    DROP TABLE public."Client";
       public         heap    postgres    false            �            1259    24846    Client_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Client_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public."Client_id_seq";
       public          postgres    false    207            �           0    0    Client_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public."Client_id_seq" OWNED BY public."Client".id;
          public          postgres    false    208            �            1259    24848    Comfort    TABLE     J   CREATE TABLE public."Comfort" (
    id integer NOT NULL,
    type text
);
    DROP TABLE public."Comfort";
       public         heap    postgres    false            �            1259    24854    Comfort_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Comfort_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public."Comfort_id_seq";
       public          postgres    false    209            �           0    0    Comfort_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public."Comfort_id_seq" OWNED BY public."Comfort".id;
          public          postgres    false    210            �            1259    24856    Food_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Food_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public."Food_id_seq";
       public          postgres    false    203            �           0    0    Food_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public."Food_id_seq" OWNED BY public."Food".id;
          public          postgres    false    211            �            1259    24983    RecreationBase    TABLE     �   CREATE TABLE public."RecreationBase" (
    id integer NOT NULL,
    "idClient" integer NOT NULL,
    "idRoomNumber" integer NOT NULL,
    "startDate" date NOT NULL,
    "endDate" date NOT NULL,
    "idAdditionalServices" integer NOT NULL
);
 $   DROP TABLE public."RecreationBase";
       public         heap    postgres    false            �            1259    24981    RecreationBase1_id_seq1    SEQUENCE     �   CREATE SEQUENCE public."RecreationBase1_id_seq1"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public."RecreationBase1_id_seq1";
       public          postgres    false    219            �           0    0    RecreationBase1_id_seq1    SEQUENCE OWNED BY     U   ALTER SEQUENCE public."RecreationBase1_id_seq1" OWNED BY public."RecreationBase".id;
          public          postgres    false    218            �            1259    24861 
   RoomNumber    TABLE     �   CREATE TABLE public."RoomNumber" (
    id integer NOT NULL,
    "roomNumber" integer,
    "numberOfSeats" integer,
    price integer,
    "idComfort" integer NOT NULL,
    "idRoomStatus" integer NOT NULL
);
     DROP TABLE public."RoomNumber";
       public         heap    postgres    false            �            1259    25004    RecreationBaseView    VIEW     �  CREATE VIEW public."RecreationBaseView" AS
 SELECT rb.id,
    cl.surname,
    cl.name,
    cl.patronymic,
    cl.phone,
    rn."roomNumber",
    rb."startDate",
    rb."endDate",
    f.type,
    st."typeSt"
   FROM public."RecreationBase" rb,
    public."Client" cl,
    public."RoomNumber" rn,
    public."AdditionalServices" aserv,
    public."Food" f,
    public."SpaTreatments" st
  WHERE ((cl.id = rb."idClient") AND (rn.id = rb."idRoomNumber") AND (aserv.id = rb."idAdditionalServices") AND (f.id IN ( SELECT f22."idFood"
           FROM public."AdditionalServices" f22
          WHERE (f22.id = aserv.id))) AND (st.id IN ( SELECT f33."idSpaTreatments"
           FROM public."AdditionalServices" f33
          WHERE (f33.id = aserv.id))));
 '   DROP VIEW public."RecreationBaseView";
       public          postgres    false    202    219    219    219    219    219    212    212    207    207    204    204    203    203    202    202    219    207    207    207            �            1259    24871 
   RoomStatus    TABLE     O   CREATE TABLE public."RoomStatus" (
    id integer NOT NULL,
    status text
);
     DROP TABLE public."RoomStatus";
       public         heap    postgres    false            �            1259    24877    RoomNumberView    VIEW     *  CREATE VIEW public."RoomNumberView" AS
 SELECT rn.id,
    rn."roomNumber",
    rn."numberOfSeats",
    rn.price,
    comf.type,
    rs.status
   FROM public."RoomNumber" rn,
    public."RoomStatus" rs,
    public."Comfort" comf
  WHERE ((comf.id = rn."idComfort") AND (rs.id = rn."idRoomStatus"));
 #   DROP VIEW public."RoomNumberView";
       public          postgres    false    213    213    209    209    212    212    212    212    212    212            �            1259    24881    RoomNumber_id_seq    SEQUENCE     �   CREATE SEQUENCE public."RoomNumber_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public."RoomNumber_id_seq";
       public          postgres    false    212            �           0    0    RoomNumber_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public."RoomNumber_id_seq" OWNED BY public."RoomNumber".id;
          public          postgres    false    215            �            1259    24883    RoomStatus_id_seq    SEQUENCE     �   CREATE SEQUENCE public."RoomStatus_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public."RoomStatus_id_seq";
       public          postgres    false    213            �           0    0    RoomStatus_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public."RoomStatus_id_seq" OWNED BY public."RoomStatus".id;
          public          postgres    false    216            �            1259    24885    SpaTreatments_id_seq    SEQUENCE     �   CREATE SEQUENCE public."SpaTreatments_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public."SpaTreatments_id_seq";
       public          postgres    false    204            �           0    0    SpaTreatments_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public."SpaTreatments_id_seq" OWNED BY public."SpaTreatments".id;
          public          postgres    false    217            �
           2604    24887    AdditionalServices id    DEFAULT     �   ALTER TABLE ONLY public."AdditionalServices" ALTER COLUMN id SET DEFAULT nextval('public."AdditionalServices_id_seq"'::regclass);
 F   ALTER TABLE public."AdditionalServices" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    206    202            �
           2604    24888 	   Client id    DEFAULT     j   ALTER TABLE ONLY public."Client" ALTER COLUMN id SET DEFAULT nextval('public."Client_id_seq"'::regclass);
 :   ALTER TABLE public."Client" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    208    207            �
           2604    24889 
   Comfort id    DEFAULT     l   ALTER TABLE ONLY public."Comfort" ALTER COLUMN id SET DEFAULT nextval('public."Comfort_id_seq"'::regclass);
 ;   ALTER TABLE public."Comfort" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    209            �
           2604    24890    Food id    DEFAULT     f   ALTER TABLE ONLY public."Food" ALTER COLUMN id SET DEFAULT nextval('public."Food_id_seq"'::regclass);
 8   ALTER TABLE public."Food" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    211    203            �
           2604    24986    RecreationBase id    DEFAULT     |   ALTER TABLE ONLY public."RecreationBase" ALTER COLUMN id SET DEFAULT nextval('public."RecreationBase1_id_seq1"'::regclass);
 B   ALTER TABLE public."RecreationBase" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    219    219            �
           2604    24892    RoomNumber id    DEFAULT     r   ALTER TABLE ONLY public."RoomNumber" ALTER COLUMN id SET DEFAULT nextval('public."RoomNumber_id_seq"'::regclass);
 >   ALTER TABLE public."RoomNumber" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    212            �
           2604    24893    RoomStatus id    DEFAULT     r   ALTER TABLE ONLY public."RoomStatus" ALTER COLUMN id SET DEFAULT nextval('public."RoomStatus_id_seq"'::regclass);
 >   ALTER TABLE public."RoomStatus" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    213            �
           2604    24894    SpaTreatments id    DEFAULT     x   ALTER TABLE ONLY public."SpaTreatments" ALTER COLUMN id SET DEFAULT nextval('public."SpaTreatments_id_seq"'::regclass);
 A   ALTER TABLE public."SpaTreatments" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    204            u          0    24819    AdditionalServices 
   TABLE DATA           O   COPY public."AdditionalServices" (id, "idFood", "idSpaTreatments") FROM stdin;
    public          postgres    false    202   �       y          0    24840    Client 
   TABLE DATA           H   COPY public."Client" (id, surname, name, patronymic, phone) FROM stdin;
    public          postgres    false    207   �       {          0    24848    Comfort 
   TABLE DATA           -   COPY public."Comfort" (id, type) FROM stdin;
    public          postgres    false    209   ��       v          0    24822    Food 
   TABLE DATA           *   COPY public."Food" (id, type) FROM stdin;
    public          postgres    false    203   �       �          0    24983    RecreationBase 
   TABLE DATA           z   COPY public."RecreationBase" (id, "idClient", "idRoomNumber", "startDate", "endDate", "idAdditionalServices") FROM stdin;
    public          postgres    false    219   p�       ~          0    24861 
   RoomNumber 
   TABLE DATA           m   COPY public."RoomNumber" (id, "roomNumber", "numberOfSeats", price, "idComfort", "idRoomStatus") FROM stdin;
    public          postgres    false    212   ��                 0    24871 
   RoomStatus 
   TABLE DATA           2   COPY public."RoomStatus" (id, status) FROM stdin;
    public          postgres    false    213   ��       w          0    24828    SpaTreatments 
   TABLE DATA           7   COPY public."SpaTreatments" (id, "typeSt") FROM stdin;
    public          postgres    false    204   1�       �           0    0    AdditionalServices_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public."AdditionalServices_id_seq"', 3, true);
          public          postgres    false    206            �           0    0    Client_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public."Client_id_seq"', 2, true);
          public          postgres    false    208            �           0    0    Comfort_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public."Comfort_id_seq"', 3, true);
          public          postgres    false    210            �           0    0    Food_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public."Food_id_seq"', 5, true);
          public          postgres    false    211            �           0    0    RecreationBase1_id_seq1    SEQUENCE SET     G   SELECT pg_catalog.setval('public."RecreationBase1_id_seq1"', 5, true);
          public          postgres    false    218            �           0    0    RoomNumber_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public."RoomNumber_id_seq"', 8, true);
          public          postgres    false    215            �           0    0    RoomStatus_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public."RoomStatus_id_seq"', 5, true);
          public          postgres    false    216            �           0    0    SpaTreatments_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public."SpaTreatments_id_seq"', 5, true);
          public          postgres    false    217            �
           2606    24896 *   AdditionalServices AdditionalServices_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public."AdditionalServices"
    ADD CONSTRAINT "AdditionalServices_pkey" PRIMARY KEY (id);
 X   ALTER TABLE ONLY public."AdditionalServices" DROP CONSTRAINT "AdditionalServices_pkey";
       public            postgres    false    202            �
           2606    24898    Client Client_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public."Client"
    ADD CONSTRAINT "Client_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public."Client" DROP CONSTRAINT "Client_pkey";
       public            postgres    false    207            �
           2606    24900    Comfort Comfort_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public."Comfort"
    ADD CONSTRAINT "Comfort_pkey" PRIMARY KEY (id);
 B   ALTER TABLE ONLY public."Comfort" DROP CONSTRAINT "Comfort_pkey";
       public            postgres    false    209            �
           2606    24902    Food Food_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public."Food"
    ADD CONSTRAINT "Food_pkey" PRIMARY KEY (id);
 <   ALTER TABLE ONLY public."Food" DROP CONSTRAINT "Food_pkey";
       public            postgres    false    203            �
           2606    24988 "   RecreationBase RecreationBase_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public."RecreationBase"
    ADD CONSTRAINT "RecreationBase_pkey" PRIMARY KEY (id);
 P   ALTER TABLE ONLY public."RecreationBase" DROP CONSTRAINT "RecreationBase_pkey";
       public            postgres    false    219            �
           2606    24906    RoomNumber RoomNumber_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public."RoomNumber"
    ADD CONSTRAINT "RoomNumber_pkey" PRIMARY KEY (id);
 H   ALTER TABLE ONLY public."RoomNumber" DROP CONSTRAINT "RoomNumber_pkey";
       public            postgres    false    212            �
           2606    24908    RoomStatus RoomStatus_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public."RoomStatus"
    ADD CONSTRAINT "RoomStatus_pkey" PRIMARY KEY (id);
 H   ALTER TABLE ONLY public."RoomStatus" DROP CONSTRAINT "RoomStatus_pkey";
       public            postgres    false    213            �
           2606    24910     SpaTreatments SpaTreatments_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public."SpaTreatments"
    ADD CONSTRAINT "SpaTreatments_pkey" PRIMARY KEY (id);
 N   ALTER TABLE ONLY public."SpaTreatments" DROP CONSTRAINT "SpaTreatments_pkey";
       public            postgres    false    204            �
           2620    25010 '   RecreationBase AutoUpdateInfoRoomNumber    TRIGGER     �   CREATE TRIGGER "AutoUpdateInfoRoomNumber" BEFORE INSERT ON public."RecreationBase" FOR EACH ROW EXECUTE FUNCTION public."AutoUpdateInfoRoomNumber"();
 D   DROP TRIGGER "AutoUpdateInfoRoomNumber" ON public."RecreationBase";
       public          postgres    false    245    219            �
           2606    24912 1   AdditionalServices AdditionalServices_idFood_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public."AdditionalServices"
    ADD CONSTRAINT "AdditionalServices_idFood_fkey" FOREIGN KEY ("idFood") REFERENCES public."Food"(id);
 _   ALTER TABLE ONLY public."AdditionalServices" DROP CONSTRAINT "AdditionalServices_idFood_fkey";
       public          postgres    false    202    2783    203            �
           2606    24917 :   AdditionalServices AdditionalServices_idSpaTreatments_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public."AdditionalServices"
    ADD CONSTRAINT "AdditionalServices_idSpaTreatments_fkey" FOREIGN KEY ("idSpaTreatments") REFERENCES public."SpaTreatments"(id);
 h   ALTER TABLE ONLY public."AdditionalServices" DROP CONSTRAINT "AdditionalServices_idSpaTreatments_fkey";
       public          postgres    false    202    204    2785            �
           2606    24989 7   RecreationBase RecreationBase_idAdditionalServices_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public."RecreationBase"
    ADD CONSTRAINT "RecreationBase_idAdditionalServices_fkey" FOREIGN KEY ("idAdditionalServices") REFERENCES public."AdditionalServices"(id);
 e   ALTER TABLE ONLY public."RecreationBase" DROP CONSTRAINT "RecreationBase_idAdditionalServices_fkey";
       public          postgres    false    2781    202    219            �
           2606    24994 +   RecreationBase RecreationBase_idClient_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public."RecreationBase"
    ADD CONSTRAINT "RecreationBase_idClient_fkey" FOREIGN KEY ("idClient") REFERENCES public."Client"(id);
 Y   ALTER TABLE ONLY public."RecreationBase" DROP CONSTRAINT "RecreationBase_idClient_fkey";
       public          postgres    false    2787    219    207            �
           2606    24999 /   RecreationBase RecreationBase_idRoomNumber_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public."RecreationBase"
    ADD CONSTRAINT "RecreationBase_idRoomNumber_fkey" FOREIGN KEY ("idRoomNumber") REFERENCES public."RoomNumber"(id);
 ]   ALTER TABLE ONLY public."RecreationBase" DROP CONSTRAINT "RecreationBase_idRoomNumber_fkey";
       public          postgres    false    219    2791    212            �
           2606    24937 $   RoomNumber RoomNumber_idComfort_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public."RoomNumber"
    ADD CONSTRAINT "RoomNumber_idComfort_fkey" FOREIGN KEY ("idComfort") REFERENCES public."Comfort"(id) NOT VALID;
 R   ALTER TABLE ONLY public."RoomNumber" DROP CONSTRAINT "RoomNumber_idComfort_fkey";
       public          postgres    false    209    2789    212            �
           2606    24942 '   RoomNumber RoomNumber_idRoomStatus_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public."RoomNumber"
    ADD CONSTRAINT "RoomNumber_idRoomStatus_fkey" FOREIGN KEY ("idRoomStatus") REFERENCES public."RoomStatus"(id) NOT VALID;
 U   ALTER TABLE ONLY public."RoomNumber" DROP CONSTRAINT "RoomNumber_idRoomStatus_fkey";
       public          postgres    false    212    213    2793            u      x�3�4�4�2�4�4����� c      y   w   x�3估����.�_�uqڅ���]��g\�waӅ�9��-��̍L���M��8/̿��bPՖ�@�;9/,��x����AS���6 巠�aj �o�`aad����� �D�      {   G   x�3�0�b߅]��8/L���¾{�x�1煅�^l���{/N��S�b���{��+F��� �L$�      v   o   x�%�=
�@��N��n�a6b�@���bg�h��;̻���̼W�J�ȶ�tV�d{G2+Y-���1j�E�R�V�����x���w�go
��"��ikf_��Dt      �   #   x�3�4��4202�50�5��3�L8�b���� e��      ~   B   x�M��	�0C�oy��g�a�����r���HUJ>ܫDjha��kc�3%�l4S�#"/��t         ,   x�3�0���/�\�{a�	��6\�{��b����� o��      w   �   x���M
�P���)�T��6�ZP��z����PZ�z��9ōi�H �d>&�2�9.s�x�յC�����f{E6s� �h�ߝ��ؚ'�$6f�f�=JG��AP/M��V�B�\��y���0Apb�6+l����W7ێ��v3{^:B     