package com.example.ranad.nodalsystems.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ranad.nodalsystems.App;
import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.adapter.OrderAdapter;
import com.example.ranad.nodalsystems.database.CartItem;
import com.example.ranad.nodalsystems.database.CartItemDao;
import com.example.ranad.nodalsystems.database.Customers;
import com.example.ranad.nodalsystems.database.CustomersDao;
import com.example.ranad.nodalsystems.database.OrderDetailDB;
import com.example.ranad.nodalsystems.database.OrderDetailDBDao;
import com.example.ranad.nodalsystems.database.Orders;
import com.example.ranad.nodalsystems.database.OrdersDao;
import com.example.ranad.nodalsystems.interfaces.OrderAction;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;
import com.example.ranad.nodalsystems.usage.DialogUtils;
import com.example.ranad.nodalsystems.usage.FragmentSwitch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OrderFragment extends Fragment implements View.OnClickListener, OrderAction {
    View view;

    AutoCompleteTextView cName, cNumber;
    EditText quantity, discount, price, order_tax;
    Spinner material, customers;
    Button submit, cancel, add_prod, save_prod, viewOrders;
    ImageView btnAddOrder;
    LinearLayout lvAddOrder;
    RecyclerView order_list;
    OrderAdapter orderAdapter;
    List<String> list = new ArrayList<String>();
    List<String> cust = new ArrayList<>();
    ScrollView lists;
    int customerId, currentItemPosition;
    AutoCompleteTextView customerAutoCompleterView;
    ArrayAdapter customerAdapter;
    List<String> customer;

    ProgressDialog progressDialog = null;

    ArrayList<OrderDetailDB> orderDetailsListDB = new ArrayList<OrderDetailDB>();
    OrderDetailDB orderDetailDB = new OrderDetailDB();


    TextView total;


    CartItemDao cartItemDao = App.getDaoSession().getCartItemDao();
    CustomersDao customersDao = App.getDaoSession().getCustomersDao();
    ArrayList<CartItem> cart = new ArrayList<>();


    public OrderFragment() {
        // Required empty public constructor
    }

    public static OrderFragment newInstance(SwitchFragment switchFragment) {
        OrderFragment fragment = new OrderFragment();
        fragment.Construct(switchFragment);
        return fragment;
    }

    public void Construct(SwitchFragment switchFragment) {

    }

    @Override
    public void onResume() {
        super.onResume();

        MainActivity.setAppTitle(R.string.order_title);
        // cart.clear();

      /*  currentItem = customers.getSelectedItemPosition();
        List<CartItem> tempCart = cartItemDao.queryBuilder().where(CartItemDao.Properties.CustomerId.eq(customerId)).list();
        cart.addAll(tempCart);
*/
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.showUpButton();
        }

        refreshTotal();
        orderAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        progressDialog = new ProgressDialog(getContext());
        view = inflater.inflate(R.layout.fragment_order, container, false);
        cName = (AutoCompleteTextView) view.findViewById(R.id.cName);
        total = (TextView) view.findViewById(R.id.total_price);
        lvAddOrder = (LinearLayout) view.findViewById(R.id.lvAddOrder);
        cNumber = (AutoCompleteTextView) view.findViewById(R.id.cNumber);
        quantity = (EditText) view.findViewById(R.id.quantity);
        discount = (EditText) view.findViewById(R.id.order_discount);
        material = (Spinner) view.findViewById(R.id.material);
        order_tax = (EditText) view.findViewById(R.id.order_tax);
        price = (EditText) view.findViewById(R.id.order_price);
        submit = (Button) view.findViewById(R.id.submit);
        submit.setOnClickListener(this);
        viewOrders = (Button) view.findViewById(R.id.viewOrders);
        viewOrders.setOnClickListener(this);
        btnAddOrder = (ImageView) view.findViewById(R.id.btnAddOrder);
        btnAddOrder.setOnClickListener(this);
        lists = (ScrollView) view.findViewById(R.id.list);
        cancel = (Button) view.findViewById(R.id.cancel_add);
        cancel.setOnClickListener(this);
        add_prod = (Button) view.findViewById(R.id.add_prod);
        add_prod.setOnClickListener(this);
        order_list = (RecyclerView) view.findViewById(R.id.order_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        order_list.setLayoutManager(linearLayoutManager);
        orderAdapter = new OrderAdapter(cart, getContext(), this);
        order_list.setAdapter(orderAdapter);
        save_prod = (Button) view.findViewById(R.id.save_prod);
        save_prod.setOnClickListener(this);
     /*   ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        material.setAdapter(dataAdapter);
*/
        customerAutoCompleterView = (AutoCompleteTextView) view.findViewById(R.id.customer_autocompleter);
        customer = new ArrayList<>();
        final List<Customers> customersList = customersDao.queryBuilder().list();
        Log.i("CCC", "customersList" + customersList.size());
        for (int i = 0; i < customersList.size(); i++) {
            customer.add(customersList.get(i).getFirstName());
            customerAdapter = new ArrayAdapter(getActivity(), R.layout.dropdown, customer);
            customerAutoCompleterView.setAdapter(customerAdapter);
            customerAutoCompleterView.setThreshold(1);
        }


        if (!customer.isEmpty())
            // new GetServiceList().execute();
            customerAutoCompleterView.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
                    if (customer.size() > 0) {
                        if (!customerAutoCompleterView.getText().toString().equals(""))
                            customerAdapter.getFilter().filter(null);
                        customerAutoCompleterView.showDropDown();
                    }
                    customerAutoCompleterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            // selectedServiceId = serviceList.get(position).getServiceId();
                            //new SelectedServiceList().execute();
                            customerId = Integer.parseInt(customersList.get(position).getId().toString());

                            cart.clear();
                            List<CartItem> tempCart = cartItemDao.queryBuilder().where(CartItemDao.Properties.CustomerId.eq(customerId)).list();
                            cart.addAll(tempCart);
                            refreshTotal();
                            orderAdapter.notifyDataSetChanged();
                            currentItemPosition = position;

                        }
                    });

                    return false;
                }
            });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
      /*  List<CartItem> deleteList = cartItemDao.queryBuilder().where(CartItemDao.Properties.CustomerId.eq(cust.get(currentItem))).list();
        cartItemDao.deleteInTx(deleteList);
        cartItemDao.insertOrReplaceInTx(cart);*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddOrder:
                lvAddOrder.setVisibility(View.VISIBLE);
                MainActivity.setAppTitle(R.string.add_order);
                btnAddOrder.setVisibility(View.GONE);
                lists.setVisibility(View.GONE);
                break;
            case R.id.cancel_add:
                lvAddOrder.setVisibility(View.GONE);
                MainActivity.setAppTitle(R.string.order_title);
                btnAddOrder.setVisibility(View.VISIBLE);
                lists.setVisibility(View.VISIBLE);
                break;
            case R.id.viewOrders:
                FragmentSwitch.switchTo(getActivity(),new OrderViewFragment(),R.string.order_title);
                break;
            case R.id.add_prod:
                if (customerId != 0) {
                    //  DialogUtils.alertDialog(getContext(), "Hi", "hi" + customerId, 1);
                    final AddProductDialog addProductDialog = new AddProductDialog();
                    addProductDialog.setOnProductAddListener(new AddProductDialog.OnProductAddListener() {
                        @Override
                        public void onProductAdd(CartItem cartItemItem) {


                            cartItemItem.setCustomerId(customerId);


                            int i = cart.indexOf(cartItemItem);

                            if (i >= 0) {
                                CartItem tempcart = cart.get(i);

                                tempcart.setQuantity(tempcart.getQuantity() + cartItemItem.getQuantity());

                                tempcart.setNetPrice(tempcart.getNetPrice() + cartItemItem.getNetPrice());

                                cart.remove(i);
                                cart.add(i, tempcart);
                                cartItemDao.update(tempcart);
                            } else {
                                cart.add(cartItemItem);
                                cartItemDao.insert(cartItemItem);
                            }
                            float cartTotal = calculateTotalCartAmount();

                            // utotal = utotal + cartTotal;
                            total.setText(cartTotal + "");


                            orderAdapter.notifyDataSetChanged();
                            addProductDialog.dismiss();
                        }
                    });
                    addProductDialog.show(getActivity().getFragmentManager(), "simple dialog");
                } else
                    DialogUtils.alertDialog(getContext(), "Validation", "Choose customer", 2);
                break;
            case R.id.save_prod:

                if (cart.size() == 0) showAlert("Alert", "Cart Empty", 1);
                else {
                    saveOrderOffline();
                    showAlert("Order Status", "Success! Saved Locally..", 2);
                }


                break;
        }

    }

    public void deleteCart(int customerId) {

        List<CartItem> deleteList = cartItemDao.queryBuilder().where(CartItemDao.Properties.CustomerId.eq(customerId)).list();
        cartItemDao.deleteInTx(deleteList);
        cart.clear();

        orderAdapter.notifyDataSetChanged();
    }


    @Override
    public void delete(int pos) {

        List<CartItem> deleteList = cartItemDao.queryBuilder().where(CartItemDao.Properties.Id.eq(cart.get(pos).getId())).list();
        cartItemDao.deleteInTx(deleteList);
        cart.remove(pos);
        refreshTotal();
        orderAdapter.notifyDataSetChanged();
    }

    @Override
    public void removeOrdersAfterSync() {

    }

    @Override
    public void saveOrderOffline() {

        List<CartItem> cartList = cartItemDao.queryBuilder().where(CartItemDao.Properties.CustomerId.eq(customerId)).list();
        OrdersDao ordersDao = App.getDaoSession().getOrdersDao();
        Orders orders = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        Date currentDate = new Date();
        orders = new Orders();

        orders.setCreatedDate(currentDate);

        orders.setCustomerId(customerId);
        orders.setTotalOrderAmount(calculateTotalCartAmount());
        long genOrderId = ordersDao.insertOrReplace(orders);

        //saving order details
        OrderDetailDBDao ordersDetailDBDao = App.getDaoSession().getOrderDetailDBDao();
        OrderDetailDB orderDetailDB = null;


        for (int i = 0; i < cartList.size(); i++) {
            orderDetailDB = new OrderDetailDB();
            orderDetailDB.setQuantity(cartList.get(i).getQuantity());
            orderDetailDB.setProductId(cartList.get(i).getProductId());
            orderDetailDB.setOrderId(Integer.parseInt(String.valueOf(genOrderId)));
            orderDetailDB.setNetPrice(cartList.get(i).getNetPrice());
            ordersDetailDBDao.insertOrReplace(orderDetailDB);
        }


        deleteCart(customerId);


        refreshTotal();
        orderAdapter.notifyDataSetChanged();
    }


    @Override
    public void syncOrderToServer() {


    }

    public void refreshTotal() {
        Log.i("refresh", "refresh");
        //  displayCart();
        total.setText("");
        total.setText(calculateTotalCartAmount() + "");
    }


    public float calculateTotalCartAmount() {
        float cartTotal = 0;
        List<CartItem> cartItemList = cartItemDao.queryBuilder().where(CartItemDao.Properties.CustomerId.eq(customerId)).list();
        for (int i = 0; i < cartItemList.size(); i++) {
            cartTotal = cartTotal + cartItemList.get(i).getNetPrice();
        }
        return cartTotal;
    }


    public void showProgress(String title, String msg, int theme) {
        progressDialog.setTitle(title);
        progressDialog.setMessage(msg);

        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    public void showAlert(String title, String msg, int theme) {


        if (theme == 1) theme = R.style.Theme_AppCompat_DayNight_Dialog;
        else theme = R.style.Theme_AppCompat_DayNight_Dialog_Alert;


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext(), theme);
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(msg)
                .setCancelable(true)
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
