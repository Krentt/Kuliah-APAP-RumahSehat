import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:rumah_sehat_app/pages/add_appotintment_page.dart';
import 'package:rumah_sehat_app/pages/auth_page.dart';
import 'package:rumah_sehat_app/pages/detail_appointment_page.dart';
import 'package:rumah_sehat_app/pages/detail_resep_page.dart';
import 'package:rumah_sehat_app/pages/home_page.dart';
import 'package:rumah_sehat_app/pages/list_appointments_page.dart';
import 'package:rumah_sehat_app/pages/pasien_profile.dart';
import 'package:rumah_sehat_app/pages/pasien_saldo.dart';
import 'package:rumah_sehat_app/pages/pasien_tagihan_list.dart';
import 'package:rumah_sehat_app/providers/appointment.dart';
import 'package:rumah_sehat_app/providers/dokters.dart';
import 'package:rumah_sehat_app/providers/pasien.dart';
import 'package:rumah_sehat_app/providers/tagihan.dart';
import 'package:rumah_sehat_app/providers/resep.dart';
import 'package:rumah_sehat_app/widgets/main_drawer.dart';
import 'providers/auth.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return  MultiProvider(providers: [
      ChangeNotifierProvider(create: (ctx) => Authh(),),
      ChangeNotifierProxyProvider<Authh, Appointments>(
          create: (context) => Appointments(),
          update: (context, auth, appointments) => appointments!..updateData(auth.token),
      ),
      ChangeNotifierProxyProvider<Authh, Dokters>(
          create: (context) => Dokters(),
          update: (context, auth, dokters) => dokters!..updateData(auth.token)),
      ChangeNotifierProxyProvider<Authh, Account>(
          create: (context) => Account(),
          update: (context, auth, account) => account!..updateData(auth.token)),
      ChangeNotifierProxyProvider<Authh, Profile>(
          create: (context) => Profile(),
          update: (context, auth, profile) => profile!..updateData(auth.token)),
      ChangeNotifierProxyProvider<Authh, PasienProvider>(
          create: (context) => PasienProvider(),
          update: (context, auth, pasien) => pasien!..updateData(auth.token)),
      ChangeNotifierProxyProvider<Authh, TagihanProvider>(
          create: (context) => TagihanProvider(),
          update: (context, auth, tagihan) => tagihan!..updateData(auth.token)),
      ChangeNotifierProxyProvider<Authh, Reseps>(
          create: (context) => Reseps(),
          update: (context, auth, reseps) => reseps!..updateData(auth.token)),

    ],
        builder: (context, child) => Consumer<Authh>
          (builder: (context, auth, child) => MaterialApp(
          debugShowCheckedModeBanner: false,
          home: auth.isAuth ? HomePage() : LoginScreen(),
          routes: {
            AddAppointmentPage.route: (ctx) => AddAppointmentPage(),
            ListAppointments.route: (ctx) => ListAppointments(),
            DetailAppointmentPage.route: (ctx) => DetailAppointmentPage(),
            PasienProfile.route: (ctx) => const PasienProfile(),
            PasienTagihan.route: (ctx) => const PasienTagihan(),
            PasienSaldo.route: (ctx) => const PasienSaldo(),
            DetailAppointmentPage.route: (ctx) => DetailAppointmentPage(),
            DetailResepPage.route: (ctx) => DetailResepPage(),
          },
        )
        ),
    );
  }
}
