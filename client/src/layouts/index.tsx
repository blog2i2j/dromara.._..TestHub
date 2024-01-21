import React, { useEffect, useState, PropsWithChildren } from 'react';
import styles from './index.less';
import { history } from 'umi';
import Iconfont from '@/components/Iconfont';
import classnames from 'classnames';
import Setting from '@/components/Setting';
import BrandLogo from '@/components/BrandLogo';
import initCaseXml from '@/components/testHub/casexml';
import i18n from '@/i18n';



interface IProps { }

interface INavItem {
  title: string;
  icon: string;
  path: string;
  openBrowser?: boolean;
}
const LNKConfig: INavItem[] = [

  {
    title: i18n("layouts.nav.case"),
    icon: '\ue6b6',
    path: '/case'
  }, {
    title: i18n("layouts.nav.assembly"),
    icon: '\ue60f',
    path: '/assembly'
  },
  // , {
  //   title: "百宝箱",
  //   icon: '\ue678',
  //   path: '/tools'
  // }, {
  //   title: "批处理",
  //   icon: '\ue611',
  //   path: '/http'
  // }, {
  //   title: "协作",
  //   icon: '\ue870',
  //   path: '/test'
  // },
  //-批处理  
  //  ｜批次管理
  //  ｜执行结果
  //-协作  
  //  ｜项目管理
  //  ｜用户管理
  //-百宝箱  
  //  ｜批次管理
  //  ｜执行结果
  {
    title: "HTTP",
    icon: '\ue63b',
    path: '/http'
  },
  // {
  //   title: i18n('home.nav.github'),
  //   icon: '\ue885',
  //   path: 'https://github.com/alibaba/Chat2DB',
  //   openBrowser: true
  // }
];

export default function BaseLayout({ children }: PropsWithChildren<IProps>) {
  const [activeNav, setActiveNav] = useState<INavItem>(LNKConfig[0]);

  useEffect(() => {
    initCaseXml();
    LNKConfig.map(item => {
      if (window.location.hash.indexOf(item.path) === 1) {
        setActiveNav(item);
      }
    })
    history.listen((location) => {
      if (location.pathname.indexOf('/case') === 0) {
        setActiveNav(LNKConfig[0])
        return
      }
      LNKConfig.map(item => {
        if (item.path === location.pathname) {
          setActiveNav(item)
        }
      })
    })
  }, []);

  function switchingNav(item: INavItem) {
    if (item.openBrowser) {
      window.open(item.path);
    } else {
      history.push(item.path);
      setActiveNav(item);
    }
  }

  function jumpHome() {
    history.push('/');
  }

  return (

    <div className={styles.page}>
      <div className={styles.layoutLeft}>
        <div className={styles.dargBox}></div>
        <BrandLogo onClick={jumpHome} className={styles.brandLogo} />
        <ul className={styles.navList}>
          {LNKConfig.map((item) => {
            return (
              <li
                key={item.path}
                className={classnames({
                  [styles.activeNav]: item.path == activeNav.path,
                })}
                onClick={switchingNav.bind(null, item)}
              >
                <Iconfont className={styles.icon} code={item.icon} />
                <div>{item.title}</div>
              </li>
            );
          })}
        </ul>
        <div className={styles.footer}>
          <Setting className={styles.setIcon}></Setting>
        </div>
      </div>
      <div className={styles.layoutRight}>
        <div className={styles.main}>{children}</div>
      </div>
    </div>
  );
}
