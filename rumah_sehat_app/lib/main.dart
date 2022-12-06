import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:rumah_sehat_app/pages/add_appotintment_page.dart';
import 'package:rumah_sehat_app/pages/auth_page.dart';
import 'package:rumah_sehat_app/pages/home_page.dart';
import 'package:rumah_sehat_app/providers/appointment.dart';
import 'package:rumah_sehat_app/providers/dokters.dart';
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
          update: (context, auth, profile) => profile!..updateData(auth.token))
    ],
        builder: (context, child) => Consumer<Authh>
          (builder: (context, auth, child) => MaterialApp(
          debugShowCheckedModeBanner: false,
          home: auth.isAuth ? HomePage() : LoginScreen(),
          routes: {
            AddAppointmentPage.route: (ctx) => AddAppointmentPage()
          },
        )
        ),
    );
  }
}
