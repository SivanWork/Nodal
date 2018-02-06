package com.example.ranad.nodalsystems.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.example.ranad.nodalsystems.CartItem;
import com.example.ranad.nodalsystems.CartItemDao;
import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.adapter.OrderAdapter;
import com.example.ranad.nodalsystems.interfaces.OrderAction;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment implements View.OnClickListener, OrderAction {
    View view;
    AutoCompleteTextView cName, cNumber;
    EditText quantity, discount, price, order_tax;
    Spinner material, customers;
    Button submit, cancel, add_prod, save_prod;
    ImageView btnAddOrder;
    LinearLayout lvAddOrder;
    RecyclerView order_list;
    OrderAdapter orderAdapter;
    List<String> list = new ArrayList<String>();
    List<String> cust = new ArrayList<>();
    ScrollView lists;
    TextView total;
    CartItemDao cartItemDao = App.getDaoSession().getCartItemDao();

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
        //cart.clear();
        currentItem = customers.getSelectedItemPosition();
        List<CartItem> tempCart = cartItemDao.queryBuilder().where(CartItemDao.Properties.CustomerId.eq(cust.get(currentItem))).list();
        cart.addAll(tempCart);
        refreshTotal();
        orderAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
        customers = (Spinner) view.findViewById(R.id.cust_spinner);
        submit = (Button) view.findViewById(R.id.submit);
        submit.setOnClickListener(this);
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

        list.add("Item 1");
        list.add("Item 2");
        list.add("Item 3");
        list.add("Item 4");
        list.add("Item 5");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        material.setAdapter(dataAdapter);

        cust.add("Customer 1");
        cust.add("Customer 2");
        cust.add("Customer 3");
        cust.add("Customer 4");
        cust.add("Customer 5");
        ArrayAdapter<String> customer = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, cust);
        customer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        customers.setAdapter(customer);
        customers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("for cus "+currentItem,cart.toString());
                Log.d("changing cust to", i + " ");

                List<CartItem> deleteList = cartItemDao.queryBuilder().where(CartItemDao.Properties.CustomerId.eq(cust.get(currentItem))).list();
                cartItemDao.deleteInTx(deleteList);
                cartItemDao.insertOrReplaceInTx(cart);
                cart.clear();
                List<CartItem> tempCart = cartItemDao.queryBuilder().where(CartItemDao.Properties.CustomerId.eq(cust.get(i))).list();
                cart.addAll(tempCart);
                refreshTotal();
                orderAdapter.notifyDataSetChanged();
                currentItem = i;
                List<CartItem> cartItems = cartItemDao.loadAll();
                Log.d("all records",cartItems.toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }


    @Override
    public void onPause() {
        super.onPause();
        List<CartItem> deleteList = cartItemDao.queryBuilder().where(CartItemDao.Properties.CustomerId.eq(cust.get(currentItem))).list();
        cartItemDao.deleteInTx(deleteList);
        cartItemDao.insertOrReplaceInTx(cart);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    int currentItem=-1;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
              /* OrderApi create = ApiClient.createorders(getContext());
               OrderPojo orderPojo = new OrderPojo();
                Call<OrderPojo> call = create.createOrder(orderPojo);
                call.enqueue(new Callback<OrderPojo>() {
                    @Override
                    public void onResponse(Call<OrderPojo> call, Response<OrderPojo> response) {
                        Log.d("response", response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<OrderPojo> call, Throwable t) {

                    }
                });*/


                break;
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
            case R.id.add_prod:
                final AddProductDialog addProductDialog = new AddProductDialog();
                addProductDialog.setOnProductAddListener(new AddProductDialog.OnProductAddListener() {
                    @Override
                    public void onProductAdd(CartItem cartItemItem) {
                        cartItemItem.setCustomerId(cust.get(customers.getSelectedItemPosition()));
                        int i = cart.indexOf(cartItemItem);

                        if (i >= 0) {
                            CartItem tempcart = cart.get(i);

                            tempcart.setQuantity(tempcart.getQuantity() + cartItemItem.getQuantity());
                            cart.remove(i);
                            cart.add(i, tempcart);

                            Log.d("adding t item",tempcart.toString());

                        } else {
                            Log.d("adding item",cartItemItem.toString());
                            cart.add(cartItemItem);

                        }
                        int partTotal = calculateTotalForProduct( cart.indexOf(cartItemItem));
                        int utotal = Integer.parseInt(total.getText().toString());
                        utotal=utotal+partTotal;
                        total.setText(utotal+"");
                        orderAdapter.notifyDataSetChanged();
                        addProductDialog.dismiss();
                    }
                });
                addProductDialog.show(getActivity().getFragmentManager(), "simple dialog");
                break;
            case R.id.save_prod:

                break;
        }
    }

    ArrayList<CartItem> cart = new ArrayList<>();

    @Override
    public void delete(int pos) {
        int partTotal = calculateTotalForProduct( pos);
        int utotal = Integer.parseInt(total.getText().toString());
        utotal=utotal-partTotal;
        total.setText(utotal+"");
        cart.remove(pos);
        orderAdapter.notifyDataSetChanged();
    }

    public void refreshTotal(){
        int sum=0;
        for(int i= 0; i<cart.size(); i++){
            sum = sum+ (12345 * cart.get(i).getQuantity());
        }
        total.setText(sum+"");
    }

    public int calculateTotalForProduct(int positionOfProduct){
      int partTotal= 12345 * cart.get(positionOfProduct).getQuantity();
      return partTotal;
    }
}
