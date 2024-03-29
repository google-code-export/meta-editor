/*
 * Metadata Editor
 * @author Jiri Kremser
 * 
 * 
 * 
 * Metadata Editor - Rich internet application for editing metadata.
 * Copyright (C) 2011  Jiri Kremser (kremser@mzk.cz)
 * Moravian Library in Brno
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * 
 */

package cz.fi.muni.xkremser.editor.server.guice;

import javax.xml.namespace.NamespaceContext;

import com.google.inject.Scopes;
import com.google.inject.name.Names;
import com.gwtplatform.dispatch.server.guice.HandlerModule;

import cz.fi.muni.xkremser.editor.server.AuthenticationServlet;
import cz.fi.muni.xkremser.editor.server.OAIPMHClient;
import cz.fi.muni.xkremser.editor.server.OAIPMHClientImpl;
import cz.fi.muni.xkremser.editor.server.ServerUtils;
import cz.fi.muni.xkremser.editor.server.URLS;
import cz.fi.muni.xkremser.editor.server.Z3950Client;
import cz.fi.muni.xkremser.editor.server.Z3950ClientImpl;
import cz.fi.muni.xkremser.editor.server.DAO.DBSchemaDAO;
import cz.fi.muni.xkremser.editor.server.DAO.DBSchemaDAOImpl;
import cz.fi.muni.xkremser.editor.server.DAO.ImageResolverDAO;
import cz.fi.muni.xkremser.editor.server.DAO.ImageResolverDAOImpl;
import cz.fi.muni.xkremser.editor.server.DAO.InputQueueItemDAO;
import cz.fi.muni.xkremser.editor.server.DAO.InputQueueItemDAOImpl;
import cz.fi.muni.xkremser.editor.server.DAO.LockDAO;
import cz.fi.muni.xkremser.editor.server.DAO.LockDAOImpl;
import cz.fi.muni.xkremser.editor.server.DAO.RecentlyModifiedItemDAO;
import cz.fi.muni.xkremser.editor.server.DAO.RecentlyModifiedItemDAOImpl;
import cz.fi.muni.xkremser.editor.server.DAO.RequestDAO;
import cz.fi.muni.xkremser.editor.server.DAO.RequestDAOImpl;
import cz.fi.muni.xkremser.editor.server.DAO.StoredItemsDAO;
import cz.fi.muni.xkremser.editor.server.DAO.StoredItemsDAOImpl;
import cz.fi.muni.xkremser.editor.server.DAO.TreeStructureDAO;
import cz.fi.muni.xkremser.editor.server.DAO.TreeStructureDAOImpl;
import cz.fi.muni.xkremser.editor.server.DAO.UserDAO;
import cz.fi.muni.xkremser.editor.server.DAO.UserDAOImpl;
import cz.fi.muni.xkremser.editor.server.config.EditorConfiguration;
import cz.fi.muni.xkremser.editor.server.config.EditorConfigurationImpl;
import cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess;
import cz.fi.muni.xkremser.editor.server.fedora.FedoraAccessImpl;
import cz.fi.muni.xkremser.editor.server.fedora.FedoraNamespaceContext;
import cz.fi.muni.xkremser.editor.server.fedora.IPaddressChecker;
import cz.fi.muni.xkremser.editor.server.fedora.RequestIPaddressChecker;
import cz.fi.muni.xkremser.editor.server.fedora.SecuredFedoraAccessImpl;
import cz.fi.muni.xkremser.editor.server.fedora.utils.FedoraUtils;
import cz.fi.muni.xkremser.editor.server.handler.ChangeRightsHandler;
import cz.fi.muni.xkremser.editor.server.handler.CheckAndUpdateDBSchemaHandler;
import cz.fi.muni.xkremser.editor.server.handler.CheckAvailabilityHandler;
import cz.fi.muni.xkremser.editor.server.handler.ConvertToJPEG2000Handler;
import cz.fi.muni.xkremser.editor.server.handler.DownloadDigitalObjectDetailHandler;
import cz.fi.muni.xkremser.editor.server.handler.FindAltoOcrFilesHandler;
import cz.fi.muni.xkremser.editor.server.handler.FindMetadataHandler;
import cz.fi.muni.xkremser.editor.server.handler.GetAllRequestItemsHandler;
import cz.fi.muni.xkremser.editor.server.handler.GetAllRolesHandler;
import cz.fi.muni.xkremser.editor.server.handler.GetClientConfigHandler;
import cz.fi.muni.xkremser.editor.server.handler.GetDOModelHandler;
import cz.fi.muni.xkremser.editor.server.handler.GetDescriptionHandler;
import cz.fi.muni.xkremser.editor.server.handler.GetDigitalObjectDetailHandler;
import cz.fi.muni.xkremser.editor.server.handler.GetFullImgMetadataHandler;
import cz.fi.muni.xkremser.editor.server.handler.GetIngestInfoHandler;
import cz.fi.muni.xkremser.editor.server.handler.GetLockInformationHandler;
import cz.fi.muni.xkremser.editor.server.handler.GetLoggedUserHandler;
import cz.fi.muni.xkremser.editor.server.handler.GetRecentlyModifiedHandler;
import cz.fi.muni.xkremser.editor.server.handler.GetRelationshipsHandler;
import cz.fi.muni.xkremser.editor.server.handler.GetUserInfoHandler;
import cz.fi.muni.xkremser.editor.server.handler.GetUserRolesAndIdentitiesHandler;
import cz.fi.muni.xkremser.editor.server.handler.InsertNewDigitalObjectHandler;
import cz.fi.muni.xkremser.editor.server.handler.LockDigitalObjectHandler;
import cz.fi.muni.xkremser.editor.server.handler.LogoutHandler;
import cz.fi.muni.xkremser.editor.server.handler.PutDescriptionHandler;
import cz.fi.muni.xkremser.editor.server.handler.PutDigitalObjectDetailHandler;
import cz.fi.muni.xkremser.editor.server.handler.PutRecentlyModifiedHandler;
import cz.fi.muni.xkremser.editor.server.handler.PutUserIdentityHandler;
import cz.fi.muni.xkremser.editor.server.handler.PutUserInfoHandler;
import cz.fi.muni.xkremser.editor.server.handler.PutUserRoleHandler;
import cz.fi.muni.xkremser.editor.server.handler.RemoveDigitalObjectHandler;
import cz.fi.muni.xkremser.editor.server.handler.RemoveRequestItemHandler;
import cz.fi.muni.xkremser.editor.server.handler.RemoveUserIdentityHandler;
import cz.fi.muni.xkremser.editor.server.handler.RemoveUserInfoHandler;
import cz.fi.muni.xkremser.editor.server.handler.RemoveUserRoleHandler;
import cz.fi.muni.xkremser.editor.server.handler.ScanFolderHandler;
import cz.fi.muni.xkremser.editor.server.handler.ScanInputQueueHandler;
import cz.fi.muni.xkremser.editor.server.handler.StoreTreeStructureHandler;
import cz.fi.muni.xkremser.editor.server.handler.StoredItemsHandler;
import cz.fi.muni.xkremser.editor.server.handler.UnlockDigitalObjectHandler;
import cz.fi.muni.xkremser.editor.server.modelHandler.FedoraDigitalObjectHandler;
import cz.fi.muni.xkremser.editor.server.modelHandler.FedoraDigitalObjectHandlerImpl;
import cz.fi.muni.xkremser.editor.server.modelHandler.StoredDigitalObjectHandler;
import cz.fi.muni.xkremser.editor.server.modelHandler.StoredDigitalObjectHandlerImpl;
import cz.fi.muni.xkremser.editor.server.newObject.CreateObjectUtils;

import cz.fi.muni.xkremser.editor.shared.rpc.action.ChangeRightsAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.CheckAndUpdateDBSchemaAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.CheckAvailabilityAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.ConvertToJPEG2000Action;
import cz.fi.muni.xkremser.editor.shared.rpc.action.DownloadDigitalObjectDetailAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.FindAltoOcrFilesAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.FindMetadataAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.GetAllRequestItemsAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.GetAllRolesAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.GetClientConfigAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.GetDOModelAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.GetDescriptionAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.GetDigitalObjectDetailAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.GetFullImgMetadataAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.GetIngestInfoAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.GetLockInformationAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.GetLoggedUserAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.GetRecentlyModifiedAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.GetRelationshipsAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.GetUserInfoAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.GetUserRolesAndIdentitiesAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.InsertNewDigitalObjectAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.LockDigitalObjectAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.LogoutAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.PutDescriptionAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.PutDigitalObjectDetailAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.PutRecentlyModifiedAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.PutUserIdentityAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.PutUserInfoAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.PutUserRoleAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.RemoveDigitalObjectAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.RemoveRequestItemAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.RemoveUserIdentityAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.RemoveUserInfoAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.RemoveUserRoleAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.ScanFolderAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.ScanInputQueueAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.StoreTreeStructureAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.StoredItemsAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.UnlockDigitalObjectAction;

// TODO: Auto-generated Javadoc
/**
 * Module which binds the handlers and configurations.
 */
public class ServerModule
        extends HandlerModule {

    /*
     * (non-Javadoc)
     * @see
     * com.gwtplatform.dispatch.server.guice.HandlerModule#configureHandlers()
     */
    @Override
    protected void configureHandlers() {
        bindHandler(ScanInputQueueAction.class, ScanInputQueueHandler.class);
        bindHandler(ScanFolderAction.class, ScanFolderHandler.class);
        bindHandler(GetClientConfigAction.class, GetClientConfigHandler.class);
        bindHandler(GetDigitalObjectDetailAction.class, GetDigitalObjectDetailHandler.class);
        bindHandler(PutDigitalObjectDetailAction.class, PutDigitalObjectDetailHandler.class);
        bindHandler(GetRecentlyModifiedAction.class, GetRecentlyModifiedHandler.class);
        bindHandler(PutRecentlyModifiedAction.class, PutRecentlyModifiedHandler.class);
        bindHandler(GetDescriptionAction.class, GetDescriptionHandler.class);
        bindHandler(PutDescriptionAction.class, PutDescriptionHandler.class);
        bindHandler(CheckAvailabilityAction.class, CheckAvailabilityHandler.class);
        bindHandler(GetUserInfoAction.class, GetUserInfoHandler.class);
        bindHandler(PutUserInfoAction.class, PutUserInfoHandler.class);
        bindHandler(RemoveUserInfoAction.class, RemoveUserInfoHandler.class);
        bindHandler(GetUserRolesAndIdentitiesAction.class, GetUserRolesAndIdentitiesHandler.class);
        bindHandler(PutUserIdentityAction.class, PutUserIdentityHandler.class);
        bindHandler(RemoveUserIdentityAction.class, RemoveUserIdentityHandler.class);
        bindHandler(PutUserRoleAction.class, PutUserRoleHandler.class);
        bindHandler(RemoveUserRoleAction.class, RemoveUserRoleHandler.class);
        bindHandler(GetAllRolesAction.class, GetAllRolesHandler.class);
        bindHandler(GetLoggedUserAction.class, GetLoggedUserHandler.class);
        bindHandler(GetAllRequestItemsAction.class, GetAllRequestItemsHandler.class);
        bindHandler(RemoveRequestItemAction.class, RemoveRequestItemHandler.class);
        bindHandler(FindMetadataAction.class, FindMetadataHandler.class);
        bindHandler(ConvertToJPEG2000Action.class, ConvertToJPEG2000Handler.class);
        bindHandler(LockDigitalObjectAction.class, LockDigitalObjectHandler.class);
        bindHandler(UnlockDigitalObjectAction.class, UnlockDigitalObjectHandler.class);
        bindHandler(DownloadDigitalObjectDetailAction.class, DownloadDigitalObjectDetailHandler.class);
        bindHandler(StoredItemsAction.class, StoredItemsHandler.class);
        bindHandler(GetDOModelAction.class, GetDOModelHandler.class);
        bindHandler(GetLockInformationAction.class, GetLockInformationHandler.class);
        bindHandler(InsertNewDigitalObjectAction.class, InsertNewDigitalObjectHandler.class);
        bindHandler(GetRelationshipsAction.class, GetRelationshipsHandler.class);
        bindHandler(RemoveDigitalObjectAction.class, RemoveDigitalObjectHandler.class);
        bindHandler(GetIngestInfoAction.class, GetIngestInfoHandler.class);
        bindHandler(CheckAndUpdateDBSchemaAction.class, CheckAndUpdateDBSchemaHandler.class);
        bindHandler(LogoutAction.class, LogoutHandler.class);
        bindHandler(FindAltoOcrFilesAction.class, FindAltoOcrFilesHandler.class);
        bindHandler(StoreTreeStructureAction.class, StoreTreeStructureHandler.class);
        bindHandler(ChangeRightsAction.class, ChangeRightsHandler.class);
        bindHandler(GetFullImgMetadataAction.class, GetFullImgMetadataHandler.class);

        // bind(Log.class).toProvider(LogProvider.class).in(Singleton.class);
        bind(EditorConfiguration.class).to(EditorConfigurationImpl.class).asEagerSingleton();

        // DAO
        bind(InputQueueItemDAO.class).to(InputQueueItemDAOImpl.class).asEagerSingleton();
        bind(ImageResolverDAO.class).to(ImageResolverDAOImpl.class).asEagerSingleton();
        bind(RecentlyModifiedItemDAO.class).to(RecentlyModifiedItemDAOImpl.class).asEagerSingleton();
        bind(UserDAO.class).to(UserDAOImpl.class).asEagerSingleton();
        bind(RequestDAO.class).to(RequestDAOImpl.class).asEagerSingleton();
        bind(LockDAO.class).to(LockDAOImpl.class).asEagerSingleton();
        bind(StoredItemsDAO.class).to(StoredItemsDAOImpl.class).asEagerSingleton();
        bind(DBSchemaDAO.class).to(DBSchemaDAOImpl.class).asEagerSingleton();
        bind(TreeStructureDAO.class).to(TreeStructureDAOImpl.class).asEagerSingleton();
        // bind(HibernateConnection.class).toProvider(ConnectionProvider.class).in(Scopes.SINGLETON);

        // Fedora
        bind(FedoraAccess.class).annotatedWith(Names.named("rawFedoraAccess")).to(FedoraAccessImpl.class)
                .in(Scopes.SINGLETON);
        bind(FedoraAccess.class).annotatedWith(Names.named("securedFedoraAccess"))
                .to(SecuredFedoraAccessImpl.class).in(Scopes.SINGLETON);
        bind(NamespaceContext.class).to(FedoraNamespaceContext.class).in(Scopes.SINGLETON);

        bind(FedoraDigitalObjectHandler.class).to(FedoraDigitalObjectHandlerImpl.class);
        bind(StoredDigitalObjectHandler.class).to(StoredDigitalObjectHandlerImpl.class);
        bind(Z3950Client.class).to(Z3950ClientImpl.class);
        bind(OAIPMHClient.class).to(OAIPMHClientImpl.class);

        bind(IPaddressChecker.class).to(RequestIPaddressChecker.class);
        // bind(OpenIDServlet.Callback.class).to(OpenIDCallback.class);

        // static injection
        requestStaticInjection(FedoraUtils.class);
        requestStaticInjection(AuthenticationServlet.class);
        requestStaticInjection(URLS.class);
        requestStaticInjection(CreateObjectUtils.class);
        requestStaticInjection(ServerUtils.class);

    }
}