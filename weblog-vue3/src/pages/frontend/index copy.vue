<template>
    <Header></Header>
    <!--主内容区域-->
    <main class="container max-w-screen-xl mx-auto p-4">
        <!--  grid 表格布局，分为 4 列，元素间隔为 gap-4 -->
        <div class="grid grid-cols-4 gap-7">
            <!-- 左边栏，占用 3 列 -->
            <div class="col-span-4  md:col-span-3 mb-3">

                <!-- 文章列表，grid 表格布局，分为 2 列 -->
                <div class="grid grid-cols-2 gap-4">
                    <div v-for="(article, index) in articles" :key="index" class="col-span-2 md:col-span-1 ">
                        <div
                            class="bg-white h-full border border-gray-200 rounded-lg dark:bg-gray-800 dark:border-gray-700">
                            <!--文章封面-->
                            <a href="#">
                                <!--注意此处是:src 而不是src-->
                                <img class="rounded-t-lg h-48 w-full" :src="article.cover" />
                            </a>
                            <div class="p-5">
                                <!-- 标签 -->
                                <div class="mb-3">
                                    <span v-for="tag, tagIndex in article.tags" :key="tagIndex"
                                        class="cursor-pointer bg-green-100 text-green-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded hover:bg-green-200 hover:text-green-900 dark:bg-green-900 dark:text-green-300">
                                        {{ tag.name }}
                                    </span>
                                </div>
                                <!-- 文章标题 -->
                                <a href="#">
                                    <h2 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">
                                        {{ article.title }}</h2>
                                </a>
                                <!-- 文章摘要 -->
                                <p v-if="article.summary" class="mb-3 font-normal text-gray-500 dark:text-gray-400">
                                    {{ article.summary }}
                                </p>
                                <!-- 文章发布时间、所属分类 -->
                                <p class="flex items-center font-normal text-gray-400 text-sm dark:text-gray-400">
                                    <!-- 发布时间 -->
                                    <svg class="inline w-3 h-3 mr-2 text-gray-400 dark:text-white" aria-hidden="true"
                                        xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                            stroke-width="2"
                                            d="M5 1v3m5-3v3m5-3v3M1 7h18M5 11h10M2 3h16a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1Z" />
                                    </svg>
                                    {{ article.createDate }}

                                    <!-- 所属分类 -->
                                    <svg class="inline w-3 h-3 ml-5 mr-2 text-gray-400 dark:text-white"
                                        aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                                        viewBox="0 0 18 18">
                                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                            stroke-width="2"
                                            d="M1 5v11a1 1 0 0 0 1 1h14a1 1 0 0 0 1-1V6a1 1 0 0 0-1-1H1Zm0 0V2a1 1 0 0 1 1-1h5.443a1 1 0 0 1 .8.4l2.7 3.6H1Z" />
                                    </svg>
                                    <a href="#" class="text-gray-400 hover:underline">{{ article.category.name }}</a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <nav aria-label="Page navigation example" class="mt-10 flex justify-center ">
                    <ul class="flex items-center -space-x-px h-10 text-base">
                        <li>
                            <a @click="getArticles(current -1 )"
                                class="flex items-center justify-center px-4 h-10 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
                                :class="[current > 1 ? '' : 'cursor-not-allowed']"
                                >
                                <span class="sr-only">上一页</span>
                                <svg class="w-3 h-3 rtl:rotate-180" aria-hidden="true"
                                    xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                        stroke-width="2" d="M5 1 1 5l4 4" />
                                </svg>
                            </a>
                        </li>
                        <!--页码-->
                        <li v-for="pageNo, index in pages" :key="index">
                            <a @click="getArticles(pageNo)"
                                class="flex items-center justify-center px-4 h-10 leading-tight bg-white border border-gray-300 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
                                :class="[pageNo == current ? 'text-blue-600 bg-blue-50 hover:bg-blue-100 hover:text-blue-700' : 'text-gray-500  hover:bg-gray-100 hover:text-gray-700']">{{
                                index + 1 }}</a>

                        </li>

                        <!-- <li>
                            <a href="#"
                                class="flex items-center justify-center px-4 h-10 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                                <span class="sr-only">Previous</span>
                                <svg class="w-3 h-3 rtl:rotate-180" aria-hidden="true"
                                    xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                        stroke-width="2" d="M5 1 1 5l4 4" />
                                </svg>
                            </a>
                        </li>
                        <li>
                            <a href="#"
                                class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">1</a>
                        </li>
                        <li>
                            <a href="#"
                                class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">2</a>
                        </li>
                        <li>
                            <a href="#" aria-current="page"
                                class="z-10 flex items-center justify-center px-4 h-10 leading-tight text-blue-600 border border-blue-300 bg-blue-50 hover:bg-blue-100 hover:text-blue-700 dark:border-gray-700 dark:bg-gray-700 dark:text-white">3</a>
                        </li>
                        <li>
                            <a href="#"
                                class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">4</a>
                        </li>
                        <li>
                            <a href="#"
                                class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">5</a>
                        </li>-->
                        <li>
                            <a @click="getArticles(current +1 )"
                                class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
                                :class="[current < pages ? '' :'cursor-not-allowed']">
                                <span class="sr-only">下一页</span>
                                <svg class="w-3 h-3 rtl:rotate-180" aria-hidden="true"
                                    xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                        stroke-width="2" d="m1 9 4-4-4-4" />
                                </svg>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>

            <!-- 右边侧边栏，占用一列 -->
            <div class="col-span-4 md:col-span-1">
                <!--博主信息-->
                <div
                    class="w-full py-5 px-2  max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
                    <div class="flex flex-col items-center">
                        <!--博主头像-->
                        <svg t="1718167901004" class="icon w-24 h-24 mb-3 rounded-full shadow" viewBox="0 0 1024 1024"
                            version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="6771" width="200" height="200">
                            <path
                                d="M286.281143 344.649143a191.488 191.488 0 1 1 270.774857-270.848 191.488 191.488 0 0 1-270.774857 270.848zM840.045714 634.075429h0z"
                                fill="#60666E" p-id="6772"></path>
                            <path
                                d="M511.049143 894.683429l44.178286-245.613715a18.285714 18.285714 0 0 1 17.993142-14.994285h266.825143A224.914286 224.914286 0 0 0 618.788571 446.390857H545.28l-124.269714 214.308572-124.342857-214.308572H224.548571A224.768 224.768 0 0 0 0 670.208v224.475429h2.194286c4.973714 26.770286 24.502857 50.102857 52.297143 58.953142l112.932571 35.474286a76.068571 76.068571 0 0 0 95.085714-50.102857v-0.585143c4.681143-14.774857 4.534857-29.988571 0.365715-43.739428h248.173714z m-356.352-69.485715V691.712h22.235428v140.361143l-22.235428-7.021714z"
                                fill="#60666E" p-id="6773"></path>
                            <path
                                d="M556.032 972.068571l48.054857-268.434285a18.285714 18.285714 0 0 1 17.993143-15.067429h380.050286a18.285714 18.285714 0 0 1 17.993143 21.504l-53.174858 296.228572H334.774857l6.363429-34.304h214.893714z"
                                fill="#60666E" p-id="6774"></path>
                        </svg>
                        <!-- <img class="w-24 h-24 mb-3 rounded-full shadow"
                            src="https://img.quanxiaoha.com/quanxiaoha/f97361c0429d4bb1bc276ab835843065.jpg"
                            alt="Bonnie image" /> -->
                        <!--博主昵称-->
                        <h5 class="mb-1 text-xl font-medium text-gray-900 dark:text-white">小七</h5>
                        <!--介绍语-->
                        <span class="text-sm text-gray-500 dark:text-gray-400">java攻城狮</span>
                        <!-- 第三方平台主页跳转（如 GitHub 等） -->
                        <div class="flex justify-center mt-5 gap-2">
                            <!-- GitHub -->
                            <svg t="1698029949662" data-tooltip-target="github-tooltip-bottom"
                                data-tooltip-placement="bottom" class="icon w-7 h-7" viewBox="0 0 1024 1024"
                                version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1447" width="200" height="200">
                                <path d="M512 512m-512 0a512 512 0 1 0 1024 0 512 512 0 1 0-1024 0Z" fill="#4186F5"
                                    p-id="1448"></path>
                                <path
                                    d="M611.944 302.056c0-15.701 2.75-30.802 7.816-44.917a384.238 384.238 0 0 0-186.11 2.956c-74.501-50.063-93.407-71.902-107.975-39.618a136.243 136.243 0 0 0-3.961 102.287 149.515 149.515 0 0 0-39.949 104.806c0 148.743 92.139 181.875 179.961 191.61a83.898 83.898 0 0 0-25.192 51.863c-40.708 22.518-91.94 8.261-115.181-32.058a83.117 83.117 0 0 0-60.466-39.98s-38.871-0.361-2.879 23.408a102.97 102.97 0 0 1 43.912 56.906s23.398 75.279 133.531 51.863v65.913c0 10.443 13.548 42.63 102.328 42.63 71.275 0 94.913-30.385 94.913-42.987V690.485a90.052 90.052 0 0 0-26.996-72.03c83.996-9.381 173.328-40.204 179.6-176.098a164.706 164.706 0 0 1-21.129 1.365c-84.07 0-152.223-63.426-152.223-141.666z"
                                    fill="#FFFFFF" p-id="1449"></path>
                                <path
                                    d="M743.554 322.765a136.267 136.267 0 0 0-3.961-102.289s-32.396-10.445-107.979 39.618a385.536 385.536 0 0 0-11.853-2.956 132.623 132.623 0 0 0-7.816 44.917c0 78.24 68.152 141.667 152.222 141.667 7.171 0 14.222-0.472 21.129-1.365 0.231-5.03 0.363-10.187 0.363-15.509a149.534 149.534 0 0 0-42.105-104.083z"
                                    fill="#FFFFFF" opacity=".4" p-id="1450"></path>
                            </svg>
                            <!-- GitHub Tooltip -->
                            <div id="github-tooltip-bottom" role="tooltip"
                                class="absolute z-10 invisible inline-block px-3 py-2 text-sm font-medium text-white bg-gray-900 rounded-lg shadow-sm opacity-0 tooltip dark:bg-gray-700">
                                我的 GitHub
                                <div class="tooltip-arrow" data-popper-arrow></div>
                            </div>
                            <svg t="1718168003395" class="icon w-7 h-7" viewBox="0 0 1024 1024" version="1.1"
                                data-tooltip-target="csdn-tooltip-bottom" data-tooltip-placement="bottom"
                                xmlns="http://www.w3.org/2000/svg" p-id="7787" width="200" height="200">
                                <path
                                    d="M512 1024C229.2224 1024 0 794.7776 0 512 0 229.2224 229.2224 0 512 0c282.7776 0 512 229.2224 512 512 0 282.7776-229.2224 512-512 512z m17.066667-413.525333c34.850133 4.352 68.778667 5.12 102.741333 2.0992 23.04-2.048 44.817067-8.362667 64.170667-21.9136 38.212267-26.794667 49.783467-85.1968 24.251733-123.050667-14.626133-21.7088-36.8128-30.344533-60.757333-35.498667-35.054933-7.543467-70.4512-5.751467-105.847467-3.413333-5.666133 0.3584-6.7584 3.072-7.236267 8.209067-3.072 32.682667-6.536533 65.314133-9.813333 97.962666-2.5088 24.814933-4.932267 49.629867-7.509333 75.605334z m53.4016-33.928534c1.962667-20.906667 3.6352-39.338667 5.4272-57.770666 1.553067-15.906133 3.413333-31.778133 4.727466-47.701334 0.3584-4.283733 1.553067-6.656 5.956267-6.382933 15.616 1.041067 31.709867 0.034133 46.728533 3.652267 36.488533 8.823467 48.725333 54.306133 23.3472 83.029333-15.8208 17.902933-36.7616 23.586133-59.255466 25.088-8.465067 0.546133-17.015467 0.085333-26.9312 0.085333zM512 434.295467c-2.184533-0.648533-3.5328-1.1776-4.932267-1.4336-37.717333-6.877867-75.690667-8.328533-113.646933-2.816-20.974933 3.037867-41.0112 9.489067-57.480533 23.330133-22.9888 19.319467-21.640533 46.848 4.4032 62.0032 13.056 7.594667 28.023467 12.509867 42.5984 17.288533 14.08 4.608 28.996267 6.826667 43.144533 11.264 12.5952 3.925333 14.011733 14.318933 3.584 22.306134-3.345067 2.56-7.441067 5.085867-11.537067 5.751466-11.195733 1.826133-22.698667 4.386133-33.826133 3.566934-24.098133-1.774933-48.042667-5.461333-72.5504-8.430934-1.365333 10.615467-2.935467 23.0912-4.5568 35.9424 4.181333 1.365333 7.68 2.730667 11.264 3.618134 33.9456 8.4992 68.386133 9.608533 102.912 5.12 20.087467-2.6112 39.4752-7.901867 56.695467-19.029334 28.603733-18.4832 36.693333-57.1904-4.676267-75.383466-14.506667-6.382933-30.190933-10.410667-45.482667-15.086934-11.4176-3.4816-23.313067-5.614933-34.525866-9.5232-9.7792-3.413333-11.144533-12.202667-3.037867-18.397866 4.6592-3.549867 10.717867-6.997333 16.384-7.3728a480.853333 480.853333 0 0 1 53.384533-0.853334c15.377067 0.699733 30.651733 3.549867 46.4896 5.5296L512 434.295467z m257.143467 2.048L750.933333 614.2976h54.152534c4.778667-45.636267 9.710933-90.7264 14.062933-135.8848 0.6144-6.365867 2.3552-8.840533 8.686933-9.0112 11.434667-0.273067 22.8864-1.979733 34.286934-1.570133 23.722667 0.853333 42.3936 9.728 38.4 43.264-2.901333 24.2688-5.597867 48.571733-8.2432 72.874666-1.092267 10.069333-1.826133 20.189867-2.730667 30.4128h55.330133c3.584-35.259733 7.9872-70.058667 10.496-104.994133 3.413333-47.4624-17.7664-73.3184-64.682666-80.213333-40.96-6.007467-81.339733-0.341333-121.5488 7.133866z m-483.498667 134.6048c-8.738133 1.297067-16.384 2.798933-24.098133 3.4816-25.6512 2.235733-51.319467 3.9424-76.305067-4.266667-13.909333-4.590933-24.6784-12.578133-29.7984-25.9584-7.901867-20.701867 0.887467-47.104 19.831467-60.3136 17.373867-12.117333 37.717333-15.9232 58.453333-15.9232 22.545067-0.017067 45.090133 2.423467 68.232533 3.84L307.2 432.298667c-15.069867-1.723733-29.4912-3.925333-43.997867-4.9152-41.0112-2.798933-80.64 2.6112-117.469866 20.462933-30.020267 14.557867-52.053333 36.010667-58.6752 68.130133-7.850667 38.144 11.537067 69.495467 51.7632 85.845334 19.1488 7.765333 39.287467 12.509867 60.0064 12.5952 24.746667 0.1024 49.493333-1.570133 74.205866-2.952534 3.106133-0.170667 8.311467-2.901333 8.669867-5.034666 1.979733-11.554133 2.730667-23.278933 3.9424-35.464534z"
                                    fill="#DD1700" p-id="7788"></path>
                            </svg>
                            <!-- CSDN Tooltip -->
                            <div id="csdn-tooltip-bottom" role="tooltip"
                                class="absolute z-10 invisible inline-block px-3 py-2 text-sm font-medium text-white bg-gray-900 rounded-lg shadow-sm opacity-0 tooltip dark:bg-gray-700">
                                我的 CSDN
                                <div class="tooltip-arrow" data-popper-arrow></div>
                            </div>
                            <svg t="1718168052845" class="icon w-7 h-7" viewBox="0 0 1024 1024" version="1.1"
                                data-tooltip-target="gitee-tooltip-bottom" data-tooltip-placement="bottom"
                                xmlns="http://www.w3.org/2000/svg" p-id="8748" width="200" height="200">
                                <path
                                    d="M512 1024C229.222 1024 0 794.778 0 512S229.222 0 512 0s512 229.222 512 512-229.222 512-512 512z m259.149-568.883h-290.74a25.293 25.293 0 0 0-25.292 25.293l-0.026 63.206c0 13.952 11.315 25.293 25.267 25.293h177.024c13.978 0 25.293 11.315 25.293 25.267v12.646a75.853 75.853 0 0 1-75.853 75.853h-240.23a25.293 25.293 0 0 1-25.267-25.293V417.203a75.853 75.853 0 0 1 75.827-75.853h353.946a25.293 25.293 0 0 0 25.267-25.292l0.077-63.207a25.293 25.293 0 0 0-25.268-25.293H417.152a189.62 189.62 0 0 0-189.62 189.645V771.15c0 13.977 11.316 25.293 25.294 25.293h372.94a170.65 170.65 0 0 0 170.65-170.65V480.384a25.293 25.293 0 0 0-25.293-25.267z"
                                    fill="#C71D23" p-id="8749"></path>
                            </svg>
                            <!-- CSDN Tooltip -->
                            <div id="gitee-tooltip-bottom" role="tooltip"
                                class="absolute z-10 invisible inline-block px-3 py-2 text-sm font-medium text-white bg-gray-900 rounded-lg shadow-sm opacity-0 tooltip dark:bg-gray-700">
                                我的 gitee
                                <div class="tooltip-arrow" data-popper-arrow></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--分类信息-->
                <div
                    class="w-full p-5 mt-3  bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
                    <!--分类标题-->
                    <h2 class="mb-2 font-bold text-gray-900 uppercase dark:text-white">分类</h2>
                    <!-- 分类列表 -->
                    <div
                        class="text-sm font-medium text-gray-600 bg-white rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-white">
                        <a href="#"
                            class="flex items-center w-full px-4 py-2 rounded-lg border-gray-200 cursor-pointer hover:bg-gray-100 hover:text-blue-700 dark:border-gray-600 dark:hover:bg-gray-600 dark:hover:text-white dark:focus:ring-gray-500 dark:focus:text-white">
                            <svg class="w-3.5 h-3.5 mr-1.5 dark:text-white" aria-hidden="true"
                                xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 21 18">
                                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                    stroke-width="2"
                                    d="M2.539 17h12.476l4-9H5m-2.461 9a1 1 0 0 1-.914-1.406L5 8m-2.461 9H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.443a1 1 0 0 1 .8.4l2.7 3.6H16a1 1 0 0 1 1 1v2H5" />
                            </svg>
                            分布式
                        </a>
                        <a href="#"
                            class="flex items-center w-full px-4 py-2 rounded-lg border-gray-200 cursor-pointer hover:bg-gray-100 hover:text-blue-700 dark:border-gray-600 dark:hover:bg-gray-600 dark:hover:text-white dark:focus:ring-gray-500 dark:focus:text-white">
                            <svg class="w-3.5 h-3.5 mr-1.5 dark:text-white" aria-hidden="true"
                                xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 21 18">
                                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                    stroke-width="2"
                                    d="M2.539 17h12.476l4-9H5m-2.461 9a1 1 0 0 1-.914-1.406L5 8m-2.461 9H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.443a1 1 0 0 1 .8.4l2.7 3.6H16a1 1 0 0 1 1 1v2H5" />
                            </svg>
                            微服务
                        </a>
                        <a href="#"
                            class="flex items-center w-full px-4 py-2 rounded-lg cursor-pointer hover:bg-gray-100 hover:text-blue-700 dark:border-gray-600 dark:hover:bg-gray-600 dark:hover:text-white dark:focus:ring-gray-500 dark:focus:text-white">
                            <svg class="w-3.5 h-3.5 mr-1.5 dark:text-white" aria-hidden="true"
                                xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 21 18">
                                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                    stroke-width="2"
                                    d="M2.539 17h12.476l4-9H5m-2.461 9a1 1 0 0 1-.914-1.406L5 8m-2.461 9H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.443a1 1 0 0 1 .8.4l2.7 3.6H16a1 1 0 0 1 1 1v2H5" />
                            </svg>
                            Docker 容器
                        </a>
                    </div>
                </div>
                <!-- 标签 -->
                <div
                    class="w-full p-5 mt-3 bg-white border border-gray-200 rounded-lg dark:bg-gray-800 dark:border-gray-700">
                    <!-- 标签标题 -->
                    <h2 class="mb-2 font-bold text-gray-900 uppercase dark:text-white">标签</h2>
                    <!-- 标签列表 -->
                    <span
                        class="inline-block mb-1 cursor-pointer bg-green-100 text-green-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded hover:bg-green-200 hover:text-green-900 dark:bg-green-900 dark:text-green-300">标签</span>
                    <span
                        class="inline-block mb-1 cursor-pointer bg-green-100 text-green-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded hover:bg-green-200 hover:text-green-900 dark:bg-green-900 dark:text-green-300">标签</span>
                    <span
                        class="inline-block mb-1 cursor-pointer bg-green-100 text-green-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded hover:bg-green-200 hover:text-green-900 dark:bg-green-900 dark:text-green-300">标签</span>
                    <span
                        class="inline-block mb-1 cursor-pointer bg-green-100 text-green-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded hover:bg-green-200 hover:text-green-900 dark:bg-green-900 dark:text-green-300">标签</span>
                    <span
                        class="inline-block mb-1 cursor-pointer bg-green-100 text-green-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded hover:bg-green-200 hover:text-green-900 dark:bg-green-900 dark:text-green-300">标签</span>
                    <span
                        class="inline-block mb-1 cursor-pointer bg-green-100 text-green-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded hover:bg-green-200 hover:text-green-900 dark:bg-green-900 dark:text-green-300">标签</span>
                </div>
            </div>
        </div>

    </main>
    <Footer></Footer>
</template>



<script setup>
import Header from '@/layouts/frontend/components/Header.vue'
import Footer from '@/layouts/frontend/components/Footer.vue';
import { initTooltips } from 'flowbite'
import { onMounted, ref } from 'vue'
import { getArticlePageList } from '@/api/frontend/article.js'
// initialize components based on data attribute selectors
onMounted(() => {
    initTooltips();
})
//文章集合
const articles = ref([])
//当前页码
const current = ref(1)
//每页显示条数
const size = ref(2)
//总文章个数
const total = ref(0)
//总页数
const pages = ref(0)
//获取指定页的文章数据
function getArticles(currentNo) {
    //上下页是否能够点击判断，当跳转上一页小于1时；则不允许跳转；当要跳转下一页且页码大于总页数时，则不允许跳转
    if(currentNo <1 || (pages.value >0 && currentNo > pages.value)) {
        console.log("非法页码")
        return
    }
    //调用分页接口渲染数据
    getArticlePageList({ current: currentNo, size: size.value }).then((res) => {
        if (res.success) {
            articles.value = res.data
            current.value = res.current
            total.value = res.total
            size.value = res.size
            pages.value = res.pages
        }
    })
}
getArticles(current.value)
</script>