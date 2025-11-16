package com.github.itdachen.framework.boot.datasource.dynamic;

/**
 * 初始化数据源
 *
 * @author 剑鸣秋朔
 * @date 2023-12-30 17:53
 */
//public class LoadDataSourceRunner implements CommandLineRunner {
//    @Resource
//    private DynamicDataSourceRouting dynamicDataSource;
//    @Resource
//    private TestDbInfoMapper testDbInfoMapper;
//    @Override
//    public void run(String... args) throws Exception {
//        List<TestDbInfo> testDbInfos = testDbInfoMapper.selectList(null);
//        if (CollectionUtils.isNotEmpty(testDbInfos)) {
//            List<DataSourceInfo> ds = new ArrayList<>();
//            for (TestDbInfo testDbInfo : testDbInfos) {
//                DataSourceEntity sourceEntity = new DataSourceEntity();
//                BeanUtils.copyProperties(testDbInfo,sourceEntity);
//                sourceEntity.setKey(testDbInfo.getName());
//                ds.add(sourceEntity);
//            }
//            dynamicDataSource.createDataSource(ds);
//        }
//    }
//}
