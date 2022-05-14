package com.example.coronareport.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.Manifest;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.coronareport.R;
import com.example.coronareport.api.ApiInterface;
import com.example.coronareport.api.RetrofitInstance;
import com.example.coronareport.data.database.RoomDatabaseInstance;
import com.example.coronareport.data.models.countryDataModel.CountryDataModel;
import com.example.coronareport.data.models.globalDataModel.GlobalDataModel;
import com.example.coronareport.databinding.GlobalFragmentBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GlobalFragment extends Fragment implements EasyPermissions.PermissionCallbacks {

    private static String TAG = "GlobalFragment";
    private GlobalFragmentBinding binding;
    private String countryCode = "eg";
    private String countryName = "egypt";
    private ApiInterface apiInterface;
    private NavController navController;
    private Boolean gpsStatus = false;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Geocoder geocoder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        binding = GlobalFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        locationEnabled();

        binding.globalBox.setVisibility(View.GONE);
        binding.gcountryBox.setVisibility(View.GONE);

        // retrofit
        RetrofitInstance retrofitInstance = new RetrofitInstance("https://disease.sh/");
        apiInterface = retrofitInstance.apiInterface();

        getGlobalDataFromDatabase();
        getCountryDataFromDatabase();
        getGlobalDataFromApi();
        getCountryDataFromApi(countryCode);

        binding.gcountryBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalFragmentDirections.ActionGlobalFragmentToCountyDetailsFragment action = GlobalFragmentDirections.actionGlobalFragmentToCountyDetailsFragment();
                action.setCOUNTRYNAME(countryName);
                navController.navigate(action);
            }
        });

        binding.symptomsBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_globalFragment_to_symptomsFragment);
            }
        });
        binding.preventionBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_globalFragment_to_preventionFragment);
            }
        });
        binding.aboutBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_globalFragment_to_aboutFragment);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    private void getGlobalDataFromDatabase() {
        GlobalDataModel globalDataModel = RoomDatabaseInstance.getInstance(getActivity()).coronaDao().getGlobalData();

        if (globalDataModel != null) {
            binding.globalShimmer.stopShimmer();
            binding.globalShimmer.setVisibility(View.GONE);
            binding.globalBox.setVisibility(View.VISIBLE);
            binding.gcountryBox.setVisibility(View.VISIBLE);
            binding.worldCasesCount.setText(NumberFormat.getNumberInstance(Locale.US).format(globalDataModel.getCases()));
            binding.worldDeathCount.setText(NumberFormat.getNumberInstance(Locale.US).format(globalDataModel.getDeaths()));
        }
    }

    private void getCountryDataFromDatabase() {
        CountryDataModel countryDataModel = RoomDatabaseInstance.getInstance(getActivity()).coronaDao().getCountryData();

        if (countryDataModel != null) {
            binding.globalShimmer.stopShimmer();
            binding.globalShimmer.setVisibility(View.GONE);
            binding.globalBox.setVisibility(View.VISIBLE);
            binding.gcountryBox.setVisibility(View.VISIBLE);
            binding.countryCasesCount.setText(String.valueOf(countryDataModel.getCases()));
            binding.countryDeathCount.setText(String.valueOf(countryDataModel.getDeaths()));
            binding.countryCriticalCount.setText(String.valueOf(countryDataModel.getCritical()));
            binding.countryRecoveredCount.setText(String.valueOf(countryDataModel.getRecovered()));
            Glide.with(getActivity())
                    .load(countryDataModel.getCountryInfo().getFlag())
                    .into(binding.gflag);

        }
    }

    private void getGlobalDataFromApi() {
        apiInterface.getGlobalData().enqueue(new Callback<GlobalDataModel>() {
            @Override
            public void onResponse(Call<GlobalDataModel> call, Response<GlobalDataModel> response) {
                if (response.isSuccessful()) {
                    binding.globalShimmer.stopShimmer();
                    binding.globalShimmer.setVisibility(View.GONE);
                    binding.globalBox.setVisibility(View.VISIBLE);
                    binding.gcountryBox.setVisibility(View.VISIBLE);
                    binding.worldCasesCount.setText(NumberFormat.getNumberInstance(Locale.US).format(response.body().getCases()));
                    binding.worldDeathCount.setText(NumberFormat.getNumberInstance(Locale.US).format(response.body().getDeaths()));
                    RoomDatabaseInstance.getInstance(getActivity())
                            .coronaDao().deleteAndInsertGlobalDataInTransaction(response.body());
                }
            }

            @Override
            public void onFailure(Call<GlobalDataModel> call, Throwable t) {

                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });


    }

    private void getCountryDataFromApi(String countryCode) {
        apiInterface.getCountryData(countryCode).enqueue(new Callback<CountryDataModel>() {
            @Override
            public void onResponse(Call<CountryDataModel> call, Response<CountryDataModel> response) {
                if (response.isSuccessful()) {
                    binding.globalShimmer.stopShimmer();
                    binding.globalShimmer.setVisibility(View.GONE);
                    binding.globalBox.setVisibility(View.VISIBLE);
                    binding.gcountryBox.setVisibility(View.VISIBLE);

                    binding.countryCasesCount.setText(String.valueOf(response.body().getCases()));
                    binding.countryDeathCount.setText(String.valueOf(response.body().getDeaths()));
                    binding.countryCriticalCount.setText(String.valueOf(response.body().getCritical()));
                    binding.countryRecoveredCount.setText(String.valueOf(response.body().getRecovered()));
                    try {
                        Glide.with(requireActivity())
                                .load(response.body().getCountryInfo().getFlag())
                                .into(binding.gflag);
                        RoomDatabaseInstance.getInstance(getActivity())
                                .coronaDao().deleteAndInsertCountryDataInTransaction(response.body());
                    } catch (Exception e) {
                        Log.i(TAG, "Exception: " + e.getLocalizedMessage());
                    }
                    countryName = response.body().getCountry();
                }
            }

            @Override
            public void onFailure(Call<CountryDataModel> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(@NonNull Location location) {
                if (location != null) {
                    Log.i(TAG, "onSuccess: " + location.toString());
                    geocoder = new Geocoder(requireActivity(), Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        Log.i(TAG, "onSuccess: " + addresses.toString());
                        binding.gcountryName.setText(addresses.get(0).getCountryName());
                        binding.subAdminName.setText(addresses.get(0).getSubAdminArea());
                        countryCode = addresses.get(0).getCountryCode();
                        getCountryDataFromApi(countryCode);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i(TAG, "onFailure: " + e.getLocalizedMessage());
                Toast.makeText(requireActivity(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            requestLocationPermissions();
        } else {
            getLastLocation();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkPermissions();
        locationEnabled();
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_DENIED
        )
            requestLocationPermissions();
    }

    private void locationEnabled() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            gpsStatus = true;
            requestLocationPermissions();
        } else {
            showAlertDialog();
        }
    }

    private void requestLocationPermissions() {
        EasyPermissions.requestPermissions(
                new PermissionRequest.Builder(requireActivity(), 007, Manifest.permission.ACCESS_FINE_LOCATION)
                        .setPositiveButtonText("OK")
                        .setNegativeButtonText("cancel")
                        .setRationale("The application needs to access your current location")
                        .build());

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.i(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());
        if (perms.size() >= 1 && requestCode == 007) {
            getLastLocation();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.i(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());
        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(getActivity(), perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        } else {
            requestLocationPermissions();
        }
    }

    private void showAlertDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(requireActivity()).create();
        alertDialog.setTitle("GPS");
        alertDialog.setMessage("You have to turn on your GPS first");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!gpsStatus) {
                    showAlertDialog();
                } else {
                    alertDialog.dismiss();
                }
            }
        });
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (!gpsStatus) {
                    showAlertDialog();
                } else {
                    alertDialog.dismiss();
                }
            }
        });
        alertDialog.show();

    }
}

