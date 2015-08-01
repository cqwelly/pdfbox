/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.pdfbox.tools.pdfdebugger.ui;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

/**
 * @author Khyrul Bashar
 *
 * A singleton class that provides zoom menu which can be used to show zoom menu in the menubar.
 * To act upon the menu item selection user of the class must add ActionListener which will check for
 * the action command and act accordingly.
 */
public class ZoomMenu extends MenuBase
{
    public static final String ZOOM_50_PERCENT = "50%";
    public static final String ZOOM_100_PERCENT = "100%";
    public static final String ZOOM_200_PERCENT = "200%";

    private static ZoomMenu instance;
    private JRadioButtonMenuItem zoom50Item;
    private JRadioButtonMenuItem zoom100Item;
    private JRadioButtonMenuItem zoom200Item;
    
    /**
     * Constructor.
     */
    private ZoomMenu()
    {
        setMenu(createZoomMenu());
    }

    /**
     * Provides the ZoomMenu instance.
     *
     * @return ZoomMenu instance.
     */
    public static ZoomMenu getInstance()
    {
        if (instance == null)
        {
            instance = new ZoomMenu();
        }
        return instance;
    }

    /**
     * Set the zoom selection.
     * @param selection String instance.
     */
    public void setZoomSelection(String selection)
    {
        if (ZOOM_50_PERCENT.equals(selection))
        {
            zoom50Item.setSelected(true);
        }
        else if (ZOOM_100_PERCENT.equals(selection))
        {
            zoom100Item.setSelected(true);
        }
        else if (ZOOM_200_PERCENT.equals(selection))
        {
            zoom200Item.setSelected(true);
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    public static boolean isZoomMenu(String actionCommand)
    {
        return ZOOM_50_PERCENT.equals(actionCommand) || ZOOM_100_PERCENT.equals(actionCommand) ||
                ZOOM_200_PERCENT.equals(actionCommand);
    }

    public static float getZoomScale()
    {
        if (instance.zoom50Item.isSelected())
        {
            return 0.5f;
        }
        if (instance.zoom100Item.isSelected())
        {
            return 1;
        }
        if (instance.zoom200Item.isSelected())
        {
            return 2;
        }
        throw new IllegalArgumentException();
    }

    public static float getZoomScale(String actionCommand)
    {
        if (ZOOM_50_PERCENT.equals(actionCommand))
        {
            return 0.5f;
        }
        else if (ZOOM_100_PERCENT.equals(actionCommand))
        {
            return 1;
        }
        else if (ZOOM_200_PERCENT.equals(actionCommand))
        {
            return 2;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    private JMenu createZoomMenu()
    {
        JMenu menu = new JMenu();
        menu.setText("Zoom");

        zoom50Item = new JRadioButtonMenuItem();
        zoom100Item = new JRadioButtonMenuItem();
        zoom200Item = new JRadioButtonMenuItem();
        zoom100Item.setSelected(true);

        ButtonGroup bg = new ButtonGroup();
        bg.add(zoom50Item);
        bg.add(zoom100Item);
        bg.add(zoom200Item);

        zoom50Item.setText(ZOOM_50_PERCENT);
        zoom100Item.setText(ZOOM_100_PERCENT);
        zoom200Item.setText(ZOOM_200_PERCENT);

        menu.add(zoom50Item);
        menu.add(zoom100Item);
        menu.add(zoom200Item);

        return menu;
    }
}
