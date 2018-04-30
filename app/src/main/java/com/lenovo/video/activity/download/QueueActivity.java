/*
 * Copyright (c) 2017 LingoChamp Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lenovo.video.activity.download;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.lenovo.video.activity.base.BaseActivity;
import com.vilyever.socketclient.SocketClient;
import com.vilyever.socketclient.helper.SocketResponsePacket;

/**
 * On this demo you will be known how to download batch tasks as a queue and download with different
 * priority.
 */
public class QueueActivity extends BaseActivity {

//    private QueueController controller;
//    private QueueRecyclerAdapter adapter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_queue);
//
//        initQueueActivity(findViewById(R.id.actionView), (TextView) findViewById(R.id.actionTv),
//                (AppCompatRadioButton) findViewById(R.id.serialRb),
//                (AppCompatRadioButton) findViewById(R.id.parallelRb),
//                (RecyclerView) findViewById(R.id.recyclerView),
//                (CardView) findViewById(R.id.deleteActionView), findViewById(R.id.deleteActionTv));
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initTools() {

    }

    @Override protected void onDestroy() {
        super.onDestroy();

//        this.controller.stop();
    }

    @Override
    protected void getMessage(SocketClient client, @NonNull SocketResponsePacket responsePacket) {

    }

//    @Override public int titleRes() {
//        return R.string.queue_download_title;
//    }
//
//    private void initQueueActivity(final View actionView, final TextView actionTv,
//                                   final AppCompatRadioButton serialRb,
//                                   final AppCompatRadioButton parallelRb,
//                                   RecyclerView recyclerView,
//                                   final CardView deleteActionView, final View deleteActionTv) {
//        initController(actionView, actionTv, serialRb, parallelRb,
//                deleteActionView, deleteActionTv);
//        initRecyclerView(recyclerView);
//        initAction(actionView, actionTv, serialRb, parallelRb, deleteActionView, deleteActionTv);
//    }
//
//    private void initController(final View actionView, final TextView actionTv,
//                                final AppCompatRadioButton serialRb,
//                                final AppCompatRadioButton parallelRb,
//                                final CardView deleteActionView, final View deleteActionTv) {
//        final QueueController controller = new QueueController();
//        this.controller = controller;
//        controller.initTasks(this, new DownloadContextListener() {
//            @Override
//            public void taskEnd(@NonNull DownloadContext context, @NonNull DownloadTask task,
//                                @NonNull EndCause cause,
//                                @android.support.annotation.Nullable Exception realCause,
//                                int remainCount) {
//            }
//
//            @Override public void queueEnd(@NonNull DownloadContext context) {
//                actionView.setTag(null);
//                actionTv.setText(R.string.start);
//                // to cancel
//                controller.stop();
//
//                serialRb.setEnabled(true);
//                parallelRb.setEnabled(true);
//
//                deleteActionView.setEnabled(true);
//                deleteActionView.setCardElevation((Float) deleteActionView.getTag());
//                deleteActionTv.setEnabled(true);
//
//                adapter.notifyDataSetChanged();
//            }
//        });
//
//    }
//
//    private void initRecyclerView(RecyclerView recyclerView) {
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        final QueueRecyclerAdapter adapter = new QueueRecyclerAdapter(controller);
//        this.adapter = adapter;
//        recyclerView.setAdapter(adapter);
//    }
//
//    private void initAction(final View actionView, final TextView actionTv,
//                            final AppCompatRadioButton serialRb,
//                            final AppCompatRadioButton parallelRb,
//                            final CardView deleteActionView, final View deleteActionTv) {
//        deleteActionView.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                controller.deleteFiles();
//                adapter.notifyDataSetChanged();
//            }
//        });
//
//        actionTv.setText(R.string.start);
//        actionView.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                final boolean started = v.getTag() != null;
//
//                if (started) {
//                    controller.stop();
//                } else {
//                    v.setTag(new Object());
//                    actionTv.setText(R.string.cancel);
//
//                    // to start
//                    controller.start(serialRb.isChecked());
//                    adapter.notifyDataSetChanged();
//
//                    serialRb.setEnabled(false);
//                    parallelRb.setEnabled(false);
//                    deleteActionView.setEnabled(false);
//                    deleteActionView.setTag(deleteActionView.getCardElevation());
//                    deleteActionView.setCardElevation(0);
//                    deleteActionTv.setEnabled(false);
//                }
//            }
//        });
//    }
}
